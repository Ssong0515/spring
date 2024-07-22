package bitc.fullstack405.jpatest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/*
@EnableJpaAuditing

JPA Audit: '감시하다'라는 의미로 각 데이터마다 누가 언제 데이터를 생성하고 변경했는지 감지하는 기능
JPA Audit 활성화
1. Application 클래스에 @EnableJpaAuditing 어노테이션 추가
2. Configuration 클래스 생성 후 @EnableJpaAuditing 어노테이션 추가
 */
@Configuration
@EnableJpaAuditing
public class JpaAuditingConfig {
}
