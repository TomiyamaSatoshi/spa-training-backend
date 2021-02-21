package training.spa.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ApplicationErrorException extends Exception {

	private String errorCode;
	private String functionName;
	private String message;
	
	
}
