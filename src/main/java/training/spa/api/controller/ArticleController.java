package training.spa.api.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import training.spa.api.domain.ArticleSearchCondition;
import training.spa.api.exception.ApplicationErrorException;
import training.spa.api.service.ArticleService;
import training.spa.api.service.AuthService;
import training.spa.api.annotation.AuthGuard;
import training.spa.api.domain.Article;
import training.spa.api.domain.ArticleCount;
import training.spa.api.domain.ArticleInfo;

@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleController extends ControllerBase{

	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private AuthService authService;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public List<ArticleInfo> getArticles(ArticleSearchCondition articleSearchCondition) {
		return articleService.getLatestArticle(articleSearchCondition);
	}
	
	@RequestMapping(value="/{articleId}", method = RequestMethod.GET)
	public ArticleInfo getArticle(@PathVariable int articleId) {
		return articleService.selectArticle(articleId);
	}
	
	@AuthGuard
	@RequestMapping(method = RequestMethod.POST)
	public Article insertArticle(@RequestHeader("Authorization") String authorization,
			@RequestBody @Valid Article article, BindingResult bindingResult) throws ApplicationErrorException, GeneralSecurityException, IOException {
		
//		for (ObjectError error : bindingResult.getAllErrors()) {
//			System.out.println(error.getDefaultMessage());
//		}
		
		// バリデーションエラーがあれば例外をThrowする
		validate("insertArticle", bindingResult.getAllErrors());
		
		Map<String, String> userAttr = authService.getUserAttr(authorization);
		article.setCreatedBy(userAttr.get("name"));
		article.setPictureUrl(userAttr.get("pictureUrl"));
		
		// 記事を登録する
		articleService.insertArticle(article);
		return articleService.selectArticle(article.getArticleId());
	}
	
	@RequestMapping(value="/nice", method = {RequestMethod.GET, RequestMethod.POST})
	public Article incrementNice(@RequestParam("articleId") int articleId) {
		Article article = articleService.selectArticle(articleId);
		
		article.setNiceCount(article.getNiceCount() + 1);
		articleService.updateArticle(article);
		
		return article;
	}
	
	@RequestMapping(value="/count", method = RequestMethod.GET)
	public ArticleCount count() {
		return articleService.countArticle();
	}
}
