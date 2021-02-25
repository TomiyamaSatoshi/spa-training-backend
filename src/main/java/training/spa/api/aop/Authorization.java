package training.spa.api.aop;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import training.spa.api.exception.ApplicationErrorException;
import training.spa.api.service.AuthService;

@Aspect
@Component
public class Authorization {
	
	private final static Logger logger = LoggerFactory.getLogger(Authorization.class);

	private final static ApplicationErrorException appException = new ApplicationErrorException("A001", "authorization", "Unauthorization access. ");
	
	@Autowired
	AuthService authService;
	
	@Before("execution(public * training.spa.api.controller.*Controller.*(..)) && @annotation(training.spa.api.annotation.AuthGuard)")
	public void authorization() throws ApplicationErrorException, GeneralSecurityException, IOException{
		RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) attributes;
		HttpServletRequest request = servletRequestAttributes.getRequest();
		
		Enumeration<String> header = request.getHeaders("Authorzation");
		
		if (!header.hasMoreElements()) {
			logger.error(appException.toString() + "Unauthorization access. ");
			throw appException;
		}
		
		String authorization = header.nextElement();
		// ここでは戻り値は利用しないが、認証トークン不正の場合はExceptionがthrowされる
		authService.getUserAttr(authorization);
	}
}
