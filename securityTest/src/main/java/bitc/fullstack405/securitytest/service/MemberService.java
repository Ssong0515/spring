package bitc.fullstack405.securitytest.service;

import bitc.fullstack405.securitytest.database.entity.Member;
import bitc.fullstack405.securitytest.database.entity.Role;
import bitc.fullstack405.securitytest.database.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public void signupMember(String userId, String userPw, String userName, String userEmail) {
        Role userRole = userId.toLowerCase().contains("admin") ? Role.ROLE_ADMIN : Role.ROLE_MEMBER;
        String encodePassword = passwordEncoder.encode(userPw);

        Member newMember = Member.builder()
                .userId(userId)
                .userPw(encodePassword)
                .userName(userName)
                .userEmail(userEmail)
                .role(userRole)
                .build();

        memberRepository.save(newMember);

    }
}
