package bitc.fullstack405.securitytest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/public")
    public String publicPage(){
        return "public";
    }

    @GetMapping("/member")
    public String memberPage(){
        return "member/profile";
    }

    @GetMapping("/admin")
    public String adminPage(){
        return "admin/manager";
    }

}
