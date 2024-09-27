package bitc.fullstack405.securitytest.service;

import bitc.fullstack405.securitytest.database.entity.Member;
import bitc.fullstack405.securitytest.database.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        // 원래는 매개변수가 usename(기본값)이지만 자바에서는 오버라이딩 시, 타입과 순서만 보기 때문에 userId로 매개변수 이름을 변경 해도 상관 없음
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException(userId + "라는 사용자가 없습니다."));

        return member;
    }
}
