package training.spa.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import training.spa.api.dao.ArticleDao;
import training.spa.api.dao.ReplyDao;
import training.spa.api.domain.ArticleSearchCondition;
import training.spa.api.domain.Reply;
import training.spa.api.domain.Article;
import training.spa.api.domain.ArticleCount;
import training.spa.api.domain.ArticleInfo;

@Service
public class ArticleService {

	@Autowired
	private ArticleDao articleDao;
	
	@Autowired
	private ReplyDao replyDao;
	
	// 最新の投稿一覧を取得する
	public List<ArticleInfo> getLatestArticle(ArticleSearchCondition articleSearchCondition) {
		List<Article> articleList = articleDao.selectLatest(articleSearchCondition);
		
		List<ArticleInfo> ret = new ArrayList<>();
		for (Article article : articleList) {
			// 返信一覧を取得する
			List<Reply> replyList = replyDao.selectByArticleId(article.getArticleId());
			
			ArticleInfo articleInfo = new ArticleInfo(article);
			articleInfo.setReplyList(replyList);
			ret.add(articleInfo);
		}
		return ret;
	}
	
	// 投稿を取得する
	public ArticleInfo selectArticle(int articleId) {
		// 指定された投稿を取得する
		Article article = articleDao.select(articleId);
		
		// 返信一覧を取得する
		List<Reply> replyList = replyDao.selectByArticleId(articleId);
		
		ArticleInfo articleInfo = new ArticleInfo(article);
		articleInfo.setReplyList(replyList);
		return articleInfo;
	}
	
	// 投稿を追加登録する
	@Transactional
	public void insertArticle(Article article) {
		articleDao.insert(article);
	}
	
	// 投稿を更新する
	public void updateArticle(Article article) {
		articleDao.update(article);
	}
	
	// 投稿の件数を取得する
	public ArticleCount countArticle() {
		return articleDao.count();
	}
	
	// 返信の追加登録する
	@Transactional
	public void insertReply(Reply reply) {
		replyDao.insert(reply);
	}
}
