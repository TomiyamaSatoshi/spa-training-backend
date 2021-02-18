package training.spa.api.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Reply {
	
	private int replyId;
	private int articleId;
	private String replyContent;
	private String pictureUrl;
	private String createdBy;
	private Timestamp createdAt;
	private Timestamp updatedAt;

}
