package training.spa.api.domain;

import java.sql.Timestamp;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class Article {
	
	private int articleId;
	@NotBlank(message="articleTitle is required.")
	private String articleTitle;
	private String articleContent;
	private int niceCount;
	private String pictureUrl;
	private String createdBy;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	
	public Article() {}

}
