package com.bitc.jwtserver.config;

import com.bitc.jwtserver.config.jwt.JwtTokenAuthenticationFilter;
import com.bitc.jwtserver.config.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {


  private final JwtTokenProvider jwtTokenProvider;

//  비밀번호를 암호화하기 해서 스프링 빈으로 등록
  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

//  JWT 토큰에 대한 정보를 스프링 시큐리티 필터 체인에서 사용하기 위해서 스프링 빈으로 등록
  @Bean
  public JwtTokenAuthenticationFilter jwtTokenAuthenticationFilter() {
    return new JwtTokenAuthenticationFilter(jwtTokenProvider);
  }

//  사용자 인증 정보를 사용하기 위해서 스프링 빈으로 등록
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

//  스프링 시큐리티에서 제외할 항목 설정
  @Bean
  public WebSecurityCustomizer configure() {
    return web -> web.ignoring()
        .requestMatchers(toH2Console())
        .requestMatchers("/static/**");
  }

//  스프링 시큐리티 세부 설정
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
//        JWT 기반 인증이기 때문에 csrf 사용 중지
        .csrf(csrf -> csrf.disable())
//        JWT 기반 인증이기 때문에 cors 사용 중지
        .cors(cors -> cors.disable())
//        JWT 기반 인증이기 때문에 기본 form 사용 중지
        .httpBasic(httpBasic -> httpBasic.disable())
//        JWT 기반 인증이기 때문에 기본 로그아웃 사용 중지
        .logout(logout -> logout.disable())
//        JWT 기반 인증이기 때문에 세션 사용 중지
        .sessionManagement(sessionManagementConfigurer -> sessionManagementConfigurer
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//        회원 가입, login을 위한 /auth/** url과 게시물 목록을 위한 /board url은 인증없이 사용 가능
        .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                .requestMatchers("/auth/**", "/board").permitAll()
//            관리자 페이지는 관리자 권한이 필요함
                .requestMatchers("/admin/**").hasRole("ADMIN")
//            사용자 페이지와 /board/** url은 ROLE_MEMBER 권한이 필요함
                .requestMatchers("/member/**", "/board/**").hasAnyRole("ADMIN", "MEMBER")
//            나머지 url은 모두 인증 받은 사용자만 사용 가능
                .anyRequest().authenticated())
//        JWT 기반 인증이기 때문에 사용자가 만든 JWT 인증 필터를 사용하도록 등록
//        addFilterBefore() : 첫번째 매개변수로 지정한 필터를 두번째 매개변수로 지정한 스프링 시큐리티 필터보다 먼저 동작
//        addFilterAfter() : 첫번째 매개변수로 지정한 필터를 두번째 매개변수로 지정한 스프링 시큐리티 필터 다음에 동작
//        UsernamePasswordAuthenticationFilter : 스프링 시큐티리의 일반적인 사용자 인증 필터(id/pw 기반 인증)
        .addFilterBefore(jwtTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
        .build();
  }
}












