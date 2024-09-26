package bitc.fullstack405.security.controller;

import bitc.fullstack405.security.database.domain.Article;
import bitc.fullstack405.security.database.dto.ArticleListViewResponse;
import bitc.fullstack405.security.database.dto.ArticleResponse;
import bitc.fullstack405.security.database.dto.ArticleViewResponse;
import bitc.fullstack405.security.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BlogViewController {

    private final BlogService blogService;

    // 게시물 목록 보기
    @GetMapping("/articles")
    public ModelAndView getArticles(){
        ModelAndView mv = new ModelAndView("ArticleList");

        // stream() : 자바 8부터 제공된 컬렉션 데이터를 사용하기 위한 메서드
        // 리스트의 데이터를 쉽게 사용할 수 있도록 해줌
        // map() : stream() 사용 시, 사용할 수 있는 반복 실행 명령으로 컬렉션의 데이터를 하나씩 꺼내어 사용 후, 결과값을 마지막에 다시 새로운 컬렉션으로 반환함. 자바스크립트의 ES6의 map() 함수와 개념적으로 동일한 기능을 가지고 있음, 지연 실행 방식을 사용하기 때문에 마지막에 toLis() 또는 toArray toString 과 같은 명령을 사용해야 컬렉션을 반환함
        // forEach(): stream() 사용 시, 사용할 수 있는 반복 실행 명령으로 컬렉션의 데이터를 하나씩 꺼내서 사용, 자바스크립트의 ES6의 forEach()와 개념적인 것 같음
        // 클래스명::new : 지정한 클래스의 생성자를 호출하여 새로운 객체로 매핑하는 방식
        // ArticleListViewResponse::new 뒤와 같음 article -> new ArticleListViewResponse(article)
        List<ArticleListViewResponse> articleList = blogService.findAll()
                .stream()
                .map(ArticleListViewResponse::new)
                .toList();

        mv.addObject("articles", articleList);

        return mv;
    }

    // 게시물 상세 보기
    @GetMapping("/articles/{id}")
    public ModelAndView getArticle(@PathVariable Long id) {

        ModelAndView mv = new ModelAndView("article");

        Article article = blogService.findById(id);
        mv.addObject("article", article);

        return mv;
    }

    // 게시물 등록 / 수정
    @GetMapping("/new-article")
    public ModelAndView newArticle(@RequestParam(required = false) Long id) {
        ModelAndView mv = new ModelAndView("newArticle");

        if (id == null) {
            mv.addObject("article", new ArticleViewResponse());
        } else {
            Article article = blogService.findById(id);
            mv.addObject("article", new ArticleViewResponse(article));
        }

        return mv;
    }
}
