package bitc.fullstack405.security.database.dto;

import bitc.fullstack405.security.database.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ArticleListViewResponse {
    private final Long id;
    private final String title;
    private final String content;

    public ArticleListViewResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
    }

}
