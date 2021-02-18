package training.spa.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import training.spa.api.domain.Reply;

@Component
@Mapper
public interface ReplyDao {
	
	List<Reply> selectAll();
	Reply select(int replyId);
	void insert(Reply reply);
	void update(Reply reply);
	void delete(Reply reply);
	
	List<Reply> selectByArticleId(int articleId);

}
