package bitc.fullstack405.securitytest.controller;

import bitc.fullstack405.securitytest.database.entity.Member;
import bitc.fullstack405.securitytest.database.entity.Role;
import bitc.fullstack405.securitytest.database.repository.MemberRepository;
import bitc.fullstack405.securitytest.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class LoginConroller {

    private final MemberService memberService;

    @GetMapping("/login")
    public String login(){
        return "auth/login";
    }

    @GetMapping("/signup")
    public String signup(){
        return "auth/signup";
    }

    @PostMapping("/signup")
    public String signupProcess(@RequestParam String userId, @RequestParam String userPw, @RequestParam String userName, @RequestParam String userEmail){

        memberService.signupMember(userId, userPw, userName, userEmail);

        return "redirect:/login";
    }

}
