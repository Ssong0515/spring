## 스프링 시큐리티 Security

> SecurityApplication 프로젝트

### yml 방식

> 기존의 `application.properties` 파일의 사용 방식을 `:`과 `tab`을 사용하여 중복 입력되는 부분을 최대한 제거 한 방식

- 기존의 속성명 그대로 모두 입력
- `.` 사용하여 구분 하던 부분 -> `:` 기호를 사용
- `:` 기호 사용 후 다음 라인으로 변경
- 들여쓰기를 통해 구분
    - ***그래서 들여쓰기 칸수 중요***
- 속성값 입력은 `=` -> `:` 기호로 그대로 사용
    - ***속성값 입력 시 반드시 공백 1칸 사용***

  ```
  server:
  port: 8080
  ```

- 동일한 부모 속성을 가질 경우 부모 속성은 1번만 입력
- 동일한 공백을 사용한 자식 속성

```yml
spring:
  application:
    name: security

# rest 방식을 사용하기 위한 설정
  mvc:
    hiddenmethod:
      filter:
        enabled: true

# H2 데이터베이스 설정
  datasource:
    driver-class-name: org.h2.Driver
    url: jdbc:h2:mem:testdb
    username: java405
    password: ${H2_PASSWORD}
# 중요 정보 부분을 운영체제의 환경변수에 추가하고 ${환경변수명} 형태로 입력하여 운영체제의 환경변수 정보값을 가져와서 사용할 수 있음

# 윈도우 환경에서는 환경변수 추가 후 반드시 재부팅

  sql:
    init:
      mode: embedded

# H2 데이터베이스 사용 시 
# - embedded 내부
# - never 외부
```

### 커스텀 DTO 클래스 생성 방법

```java
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddArticleRequest {

    private String title;
    private String content;

// DTO 클래스를 입력 받은 데이터를 기준으로 Entity 클래스로 변환
// 빌더 패턴 사용
    public Article toEntity(){
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }

}
```

- Entity 및 config에 영속성 설정

``` java
// JPA 영속성 설정(Entity)
@EntityListeners(AuditingEntityListener.class)
public class Article {

  // 영속성을 사용하여 현재 시간 입력
  @CreatedDate
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  // 영속성을 사용하여 마지막 수정 시간 입력
  @LastModifiedDate
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;
}
```

``` Java
// 영속성 사용 설정(config)
@EnableJpaAuditing
@Configuration
public class AuditingConfig {
}
```

- Entity 설정

```Java
// 컬럼명 설정 & 수정 금지
@Column(name = "id", updatable = false)
private Long id;
```

- custom entity로 쉽게 변경 해주는 방법
    - 아래와 같이 custom DTO를 어노테이션을 이용하여 쉽게 생성

```Java
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddArticleRequest {

    private String title;
    private String content;

    public Article toEntity(){
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}
```

- 아래와 같이 Entity -> custom DTO 변환을 해줌
    - steam() : map, forEach 같은 반복을 위해 써줘야 함(자바)
    - map() : 반환값 있음 (단, 자바에서는 메모리에 저장 해주기 때문에 반드시 toList, toArray 등으로 빼줘야 함)
    - forEach() : 반환값 없음
- 클래스명::new : 아래처럼 람다식과 같은 의미
    - ArticleListViewResponse::new == article -> new ArticleListViewResponse(article)

```Java
List<ArticleListViewResponse> articleList = blogService.findAll()
                .stream()
                .map(ArticleListViewResponse::new)
                .toList();
```

- ResponseEntity: 클라이언트에게 전달하는 데이터와 http 상태 및 헤더 정보를 가지고 있는 클래스
  - status(): 클라이언트에게 응답할 http 상태 정보
  - body(): 클라이언트에게 전달할 데이터
  - 아래 세개는 같음
  - 반환값이 void면 ResponseEntity<Void>이고 return 값은 ResponseEntity.ok().build() 이렇게 됨

```java
  ResponseEntity.status(HttpStatus.OK).body(articleList);
  ResponseEntity.ok().body(articleList);
  ResponseEntity.ok(articleList);
```

- orElseThrow() : Optional 객체 사용 시, Optional 객체의 값이 존재하지 않으면 예외 출력 메서드
```java
public Article findById(Long id) {
        return blogRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }
```

### Cors 설정
- addMapping() : CORS 설정을 적용할 URL 패턴을 지정

```java
public class WebMvcConfig implements WebMvcConfigurer {

//  addCorsMappings() : CORS 설정을 추가하는 메소드
//  CorsRegistry : CORS 설정을 정의하고 적용하기 위한 매개변수용 클래스
  @Override
  public void addCorsMappings(CorsRegistry registry) {
//    addMapping() : CORS 설정을 적용할 URL 패턴을 지정
//    allowedOrigins() : 허용할 URL 지정
//    allowedMethods() : 허용할 HTTP 접속 방식을 지정
//    allowedHeaders() : 허용한 HTTP 헤더를 지정, 모든 헤더를 허용 시 * 사용, 여러 헤더 사용 시 배열로 지정
//    exposedHeaders() : 브라우저가 접근할 수 있는 헤더를 지정
//    allowCredentials() : 쿠키를 주고 받을 수 있도록 설정
//    maxAge() : 브라우저가 preflight 요청을 캐시할 시간을 지정
    registry.addMapping("/api/**") // /api/** 경로에 대한 CORS 설정
        .allowedOrigins("http://localhost:8080") // 허용할 출처
        .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 HTTP 메소드
        .allowedHeaders("*") // 모든 헤더 허용
        .exposedHeaders("Authorization") // 노출할 응답 헤더
        .allowCredentials(true) // 자격 증명 허용
        .maxAge(3600); // 요청 캐시 시간을 1시간으로 설정
  }
}
```

## 스프링 시큐리티 설정

### securityConfig
- WebSecurityCustomizer: 스프링 시큐리티 상세 설정
- ignore(): 시큐리티 제외할 목록
  - requestMatchers: url 목록
```java
@Bean
public WebSecurityCustomizer webSecurityCustomizer(){
    return webSecurity -> webSecurity.ignoring()
            .requestMatchers(toH2Console())
            .requestMatchers(new AntPathRequestMatcher("/static/**"));
}
```


- SecurityFilterChain : 시큐리티 필터 체인 설정
- HttpSecurity : 시큐리티에서 HTTP 요청에 대한 보안 설정
- >csrf(cross site request forgery): 웹의 취약점을 악용하여 사용자 권한을 도용, 악의적 요청 서버로 보내는 공격 기법
  - 기본값이 able임
  - 이걸 사용 하려면 token으로 인정 해야함
  - 사용 안하면 disable()

- 스프링 6에서 HttpSecurity를 설정하는 방법이 람다식(화살표 함수)을 사용하는 방식 사용
- authorizeHttpRequests: HTTP 요청에 대한 보안 설정
- requestMatchers: 
  - 요청에 대한 보안 설정
  - 문자열로 URL 입력 받음
  - 여러 개의 URL 한번에 설정 가능
- anyRequest:
  - 모든 요청에 대한 보안 서정
  - requestMatchers로 지정한 보안 설정 제외한 모든 요청에 보안 설정

- 권한 설정
  - permitAll: 모든 사용자에게 접근 허용
  - authenticated: 인증된 사용자에게만 접근 허용
  - hasRole: 특정 권한을 가진 사용자에게만 접근 허용
  - hasAnyRole: 여러 권한 중 하나라도 가지고 있는 사용자에게 접근 허용

- formLogin: 로그인 시, form 사용 설정
  - 로그인 페이지 설정
  - loginProcessingUrl: 로그인 처리 프로세스 페이지
  - defaultSuccessUrl <-> failureUrl
    - 로그인 성공 후 이동 할 페이지(리다이렉트)
    - 첫번째 매개변수: 이동할 url
    - 두번째 매개변수: 
      - true: 지정한 페이지로 이동
      - false: 이전 페이지로 이동
  - successForwardUrl <-> failureForwardUrl
    - 로그인 성공 후 이동 할 페이지(포워드)
    - 서버에서 추가적인 처리 필요할 경우 사용

  - usernameParameter
    - 이름 입력 input 태그의 name 속성
    - 기본값은 "username"
  - passwordParameter
    - 비밀번호 입력 input 태그의 name 속성
    - 기본값은 "password"
  - successHandler
    - 로그인 성공 시, 커스텀 실패 핸들러 설정
  - failureHandler
    - 로그인 실패 시, 커스텀 실패 핸들러 설정

- logout: 로그아웃 설정
  - logoutUrl: 커스텀 로그아웃 처리 페이지 URL 
  - invalidateHttpSession: 로그아웃 시 세션 무효화 설정(기본값 true)
  - deleteCookies: 로그아웃 시, 삭제할 쿠키 설정
  - clearAuthentication : 로그아웃 시, 인증 정보 삭제 여부 설정(기본값 true)
  - Customizer.withDefaults : 스프링 시큐리티 기본 설정 사용
```java
@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {})
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/public", "/login", "/signup").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/member/**").hasAnyRole("MEMBER", "ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .usernameParameter("userId")
                        .passwordParameter("userPw")
                        .permitAll()
                )
                .logout(Customizer.withDefaults());

        return http.build();
    }
```

- 시큐리티 비밀번호 암호화 시 사용
```java
@Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
```

- 인증 관련 설정
  - UserDetailsService: 간단한 인증 요구시 사용
  - AuthenticationManager: 복잡한 인증 요구시 사용
  - DaoAuthenticationProvider
    - 데이터베이스 정보를 사용하여 사용자 인증을 진행하는 클래스
  
```java
@Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder passwordEncoder, MemberDetailsService memberDetailsService) throws Exception {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(memberDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(authenticationProvider);
    }
```

### service
- UserDetailsService
  - 사용자 인증을 처리하는 인터페이스

```java
@RequiredArgsConstructor
@Service
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException(userId + "라는 사용자가 없습니다."));

        return member;
    }
}
```

### Entity
- 위에서 반환하는 Entity가 UserDetails 형태여야 하기 때문에 그 Entity도 UserDetails를 상속 받아야 함
```java
public class Member implements UserDetails{
    ...
}
```




