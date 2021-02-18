package training.spa.api;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import training.spa.api.dao.ArticleDao;
import training.spa.api.dao.ReplyDao;
import training.spa.api.domain.Article;
import training.spa.api.domain.ArticleSearchCondition;
import training.spa.api.domain.Reply;

@SpringBootTest
class ApiApplicationTests {
	
	@Autowired
	ArticleDao articleDao;
	
	@Autowired
	ReplyDao replyDao;
	
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
		assertTrue(articleList.size() == 0);
	}

	public void reply() {
		List<Reply> replyList = replyDao.selectByArticleId(1);
		assertTrue(replyList.size() == 0);
	}
}
