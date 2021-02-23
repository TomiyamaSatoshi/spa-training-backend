package training.spa.api.domain;

import java.sql.Timestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Reply {
	
	private int replyId;
	@NotNull(message = "articleId is required.")
	private int articleId;
	@NotBlank(message = "replyContent is required.")
	private String replyContent;
	private String pictureUrl;
	private String createdBy;
	private Timestamp createdAt;
	private Timestamp updatedAt;

}
