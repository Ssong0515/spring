package bitc.fullstack405.board2.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

// Interceptor를 사용하기 위해서 HandlerInterceptor 인터페이스를 상속받아 구현
// preHandle(): 지정한 컨트롤러가 동작하기 이전에 먼저 수행됨
// postHandle(): 지정한 컨트롤러가 동작 후 view 페이지가 동작하기 이전에 수행됨
// afterCompletion(): 모든 동작이 완료 된 후 수행됨

public class LoginCheck implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest requst, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = requst.getSession();
        System.out.println("\n ========== interceptor 동작 ==========\n");

        String userId = (String) session.getAttribute("userId");
        System.out.println("User ID : " + userId);

        if (userId == null || userId.equals("")) {
            System.out.println("\n ******** interceptor ******** \n");
            System.out.println("비 로그인 상태");
            System.out.println("User ID : " + (String) session.getAttribute("userId"));

            response.sendRedirect("/board2/login/login.do");

//            컨트롤러로 연결 하지 않음
            return false;
        } else {
            System.out.println("\n ******** interceptor ******** \n");
            System.out.println("로그인 상태");
            System.out.println("User ID : " + (String) session.getAttribute("userId"));

            session.setMaxInactiveInterval(60);

//            컨트롤러로 넘어감
            return true;
        }
    }
}
