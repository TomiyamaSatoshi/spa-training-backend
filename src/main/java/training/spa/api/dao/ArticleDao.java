package training.spa.api.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import training.spa.api.domain.Article;

@Component
@Mapper
public interface ArticleDao {
	
	List<Article> selectAll();
	Article select(int articleId);
	void insert(Article article);
	void update(Article article);
	void delete(Article article);

}
