package com.bitc.jwtserver.config.jwt;

import com.bitc.jwtserver.database.entity.MemberEntity;
import com.bitc.jwtserver.database.entity.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;

@RequiredArgsConstructor
@Service
public class JwtTokenProvider {

  private final JwtProperties jwtProperties;

//  JWT 토큰 생성, 매개변수로 사용자 정보와 만료 시간을 받아서 사용
  public String generateToken(MemberEntity memberEntity, Duration expiredAt) {
    Date now = new Date();

//    실제 JWT 토큰을 생성, 매개변수로 토큰 만료시간과 사용자 정보를 받음
    return makeToken(new Date(now.getTime() + expiredAt.toMillis()), memberEntity);
  }

//  실제 JWT 토큰을 생성
  private String makeToken(Date expiry, MemberEntity memberEntity) {
    Date now = new Date();

//    JWT 토큰에 데이터를 저장하기 위한 Map 방식의 데이터를 생성
    Map<String, Object> claims = new HashMap<>();
    claims.put("id", memberEntity.getId());
    claims.put("userId", memberEntity.getUserId());
    claims.put("userNick", memberEntity.getUserNick());
    claims.put("userEmail", memberEntity.getUserEmail());
    claims.put("userRole", memberEntity.getRole());

//    빌더 패턴을 사용하여 JWT 객체 생성
//    setHeaderParam() : 토큰 타입을 설정
//    setIssuer() : 해당 토큰의 발행자 설정
//    setIssuedAt() : 해당 토큰의 생성 시간 설정
//    setExpiration() : 해당 토큰의 만료 시간 설정
//    setSubject() : 해당 토큰의 사용 주체 설정
//    addClaims() : 해당 토큰에 저장할 데이터 설정 (여러개의 데이터를 한번에 입력)
//    claim() : 해당 토큰에 저장할 데이터 설정 (한개의 데이터를 입력)
//    signWith() : 해당 토큰의 암호화 방식 설정
//    compact() : 해당 토큰의 문자열로 변환
    return Jwts.builder()
        .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
        .setIssuer(jwtProperties.getIssuer())
        .setIssuedAt(now)
        .setExpiration(expiry)
        .setSubject(memberEntity.getUserEmail())
        .addClaims(claims)
//        .claim("id", memberEntity.getId())
//        .claim("userId", memberEntity.getUserId())
//        .claim("userNick", memberEntity.getUserNick())
//        .claim("userEmail", memberEntity.getUserEmail())
//        .claim("userRole", memberEntity.getRole())
        .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
        .compact();
  }

//  JWT 토큰 검증
  public boolean validToken(String token) {
    try {
//      JWT 토큰 파싱 시작
      Jwts.parser()
//          JWT 토큰 생성 시 암호화할 때 사용할 비밀키 설정
          .setSigningKey(jwtProperties.getSecretKey())
//          JWT 토큰 데이터 파싱
          .parseClaimsJws(token);

      return true;
    } catch (Exception e) {
      return false;
    }
  }

//  매개변수로 받은 JWT 토큰에서 사용자 인증 정보를 가져옴
  public Authentication getAuthentication(String token) {
    Claims claims = getClaims(token);
    Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority(claims.get("userRole").toString()));

//    jwt 토큰을 통해서 가져온 데이터로 MemberEntity 객체 생성
    MemberEntity member = MemberEntity.builder()
        .userId(claims.get("userId").toString())
        .userNick(claims.get("userNick").toString())
        .userEmail(claims.get("userEmail").toString())
        .role(Role.valueOf(claims.get("userRole").toString()))
        .build();

//    사용자 인증 정보를 생성 후 반환
    return new UsernamePasswordAuthenticationToken(member, token, authorities);
  }

//  매개변수로 받은 JWT 토큰을 파싱하여 데이터를 출력
  private Claims getClaims(String token) {
//    JWT 토큰 파싱 시작
    return Jwts.parser()
//        암호화 시 사용한 비밀키 설정, 키가 다르면 오류 발생
        .setSigningKey(jwtProperties.getSecretKey())
//        JWT 토큰 문자열 파싱
        .parseClaimsJws(token)
//        JWT 토큰에 저장된 데이터 출력
        .getBody();
  }

//  public Long getUserId(String token) {
//    Claims claims = getClaims(token);
//    return claims.get("id", Long.class);
//  }
}












