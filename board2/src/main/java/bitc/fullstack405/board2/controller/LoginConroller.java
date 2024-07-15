package bitc.fullstack405.board2.controller;

import bitc.fullstack405.board2.dto.UserDTO;
import bitc.fullstack405.board2.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

@Controller
@RequestMapping("/board2/login")
public class LoginConroller {

    @Autowired
    private UserService userService;

    @RequestMapping("/login.do")
    public String login() throws Exception {
        return "login/Login";
    }

    @RequestMapping("/loginProcess.do")
    public String loginProcess(@RequestParam("userId") String userId, @RequestParam("userPw") String userPw, HttpServletRequest request) throws Exception {
        int result = userService.isUserInfo(userId, userPw);

        if (result == 1) {
            UserDTO user = userService.getUserInfo(userId);

            HttpSession session = request.getSession();
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("userName", user.getUserName());
            session.setAttribute("userEmail", user.getUserEmail());
            session.setMaxInactiveInterval(60 * 60 * 1);

            return "redirect:/board2/boardList.do";
        } else {
            return "redirect:/board2/login.do?errorMsg=" + URLEncoder.encode("로그인 정보가 다릅니다.", "UTF-8");
        }
    }

    @RequestMapping("/logout.do")
    public String logout(HttpServletRequest request) throws Exception {
            HttpSession session = request.getSession();
            session.invalidate();
            return "redirect:/board2/boardList.do";
    };

//    @RequestMapping("/login/signup.do")
}
