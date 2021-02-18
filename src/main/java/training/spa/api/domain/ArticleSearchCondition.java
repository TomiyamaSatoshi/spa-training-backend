package training.spa.api.domain;

import lombok.Data;

@Data
public class ArticleSearchCondition {
	// 検索結果のオフセット行番号
	private int offset;
	// オフセットからの取得件数
	private int limit;
}
