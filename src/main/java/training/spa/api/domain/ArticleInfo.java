package training.spa.api.domain;

import java.util.List;

import lombok.Data;

@Data
public class ArticleInfo extends Article{

	private List<Reply> replyList;
	
	public ArticleInfo(Article article) {
		setArticleId(article.getArticleId());
		setArticleTitle(article.getArticleTitle());
		setArticleContent(article.getArticleContent());
		setNiceCount(article.getNiceCount());
		setPictureUrl(article.getPictureUrl());
		setCreatedBy(article.getCreatedBy());
		setCreatedAt(article.getCreatedAt());
		setUpdatedAt(article.getUpdatedAt());
	}
}
