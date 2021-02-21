package training.spa.api;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import training.spa.api.dao.ArticleDao;
import training.spa.api.dao.ReplyDao;
import training.spa.api.domain.Article;
import training.spa.api.domain.ArticleInfo;
import training.spa.api.domain.ArticleSearchCondition;
import training.spa.api.domain.Reply;
import training.spa.api.service.ArticleService;

@SpringBootTest
class ApiApplicationTests {
	
	@Autowired
	ArticleDao articleDao;
	
	@Autowired
	ReplyDao replyDao;
	
	@Autowired
	ArticleService articleService;
	
	private static final Logger logger = LoggerFactory.getLogger(ApiApplicationTests.class);

	@Test
	public void article() {
		List<Article> articleList = articleDao.selectAll();
		for (Article a : articleList) {
			logger.info(a.getArticleId() + " " + a.getArticleTitle());
		}
		assertTrue(articleList != null);
	}
	
	@Test
	public void latestArticle() {
		ArticleSearchCondition articleSearchCondition = new ArticleSearchCondition();
		articleSearchCondition.setOffset(0);
		articleSearchCondition.setLimit(5);
		List<Article> articleList = articleDao.selectLatest(articleSearchCondition);
		for (Article a : articleList) {
			logger.info(a.getArticleId() + " " + a.getArticleTitle());
		}
		assertTrue(articleList.size() > 0);
	}

	@Test
	public void reply() {
		List<Reply> replyList = replyDao.selectByArticleId(3);
		for (Reply r : replyList) {
			logger.info("ID " + r.getReplyId()); 
		}
		assertTrue(replyList.size() != 0);
	}
	
	@Test
	public void articleService() {
		ArticleSearchCondition articleSearchCondition = new ArticleSearchCondition();
		articleSearchCondition.setLimit(500);
		articleSearchCondition.setOffset(0);
		
		List<ArticleInfo> articleInfoList = articleService.getLatestArticle(articleSearchCondition);
		boolean result = false;
		for (ArticleInfo a : articleInfoList) {
			logger.info("ID " + a.getArticleId() + " reply=" + a.getReplyList().size());
		}
		if (articleInfoList.size() > 0) {
			result = true;
		}
		assertTrue(result);
	}
}
