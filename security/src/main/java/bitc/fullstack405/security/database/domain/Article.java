package bitc.fullstack405.security.database.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// JPA 영속성 설정
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "article")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 컬럼명 설정 & 수정 금지
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    // 영속성을 사용하여 현재 시간 입력
    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // 영속성을 사용하여 마지막 수정 시간 입력
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public void updateArticle(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
