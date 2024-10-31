package bitc.fullstack405.quiz_aws_web_ses;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuizController {

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

}
