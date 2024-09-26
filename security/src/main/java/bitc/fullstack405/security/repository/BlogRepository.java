package bitc.fullstack405.security.repository;

import bitc.fullstack405.security.database.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
