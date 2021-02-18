package training.spa.api.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Article {
	
	private int articleId;
	private String articleTitle;
	private String articleContent;
	private int niceCount;
	private String pictureUrl;
	private String createdBy;
	private Timestamp createdAt;
	private Timestamp updatedAt;

}
