package com.bitc.jwtserver.database.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

// 사용자 정보를 저장할 MemberEntity 클래스, UserDetails 를 상속받아 구현함
@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class MemberEntity implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false)
  private Long id;

  @Column(name = "user_id", nullable = false, unique = true)
  private String userId;

  @Column(name = "user_pw", nullable = false)
  private String password;

//  컬럼명을 userName으로 설정, 스프링 시큐리티에서 내부적으로 사용자 정보를 확인하기 위한 이름이 username 으로 설정되어 있기 때문에 getUserName() 메소드가 getUsername() 로 인식되는 문제가 있기 때문에 userNick 을 사용함
  @Column(name = "user_nick")
  private String userNick;

  @Column(name = "user_email")
  private String userEmail;

  @Enumerated(EnumType.STRING)
  private Role role;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singleton(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public String getUsername() {
    return userId;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}












