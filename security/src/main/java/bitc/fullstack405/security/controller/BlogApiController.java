package bitc.fullstack405.security.controller;

import bitc.fullstack405.security.database.domain.Article;
import bitc.fullstack405.security.database.dto.AddArticleRequest;
import bitc.fullstack405.security.database.dto.ArticleResponse;
import bitc.fullstack405.security.database.dto.UpdateArticleRequest;
import bitc.fullstack405.security.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BlogApiController {

    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }

    // ResponseEntity: 클라이언트에게 전달하는 데이터와 http 상태 및 헤더 정보를 가지고 있는 클래스
    // status(): 클라이언트에게 응답할 http 상태 정보
    // body(): 클라이언트에게 전달할 데이터
    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> articleList = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        ResponseEntity.status(HttpStatus.OK).body(articleList);
        ResponseEntity.ok().body(articleList);
        ResponseEntity.ok(articleList);
        return ResponseEntity.ok().body(articleList);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable Long id) {
        Article article = blogService.findById(id);

        return ResponseEntity.ok(new ArticleResponse(article));
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        blogService.delete(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody UpdateArticleRequest request) {
        Article updatedArticle = blogService.update(id, request);

        return ResponseEntity.ok(updatedArticle);
    }
}
