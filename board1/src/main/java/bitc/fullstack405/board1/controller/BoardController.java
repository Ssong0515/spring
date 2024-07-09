package bitc.fullstack405.board1.controller;

import bitc.fullstack405.board1.dto.BoardDTO;
import bitc.fullstack405.board1.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

// @Controller: 해당 클래스가 spring WEB MVC의 Controller 파일임을 스프링 프레임워크에 알려주는 어노테이션
// JSP MVC2 방식의 Servlet 파일과 동일한 역할
// JSP Servlet 에서는 URL 하나에 Servlet 파일 하나를 연동하여 사용하였지만 Spring MVC의 Controller는 하나의 Controller 파일에 @RequestMapping을 사용하여 메소드에 URL을 연동하여 사용
// 하나의 Controller 파일에 여러개의 URL을 사용할 수 있음

// @RestController: @Controller 어노테이션과 동일한 역할을 하는 어노테이션, 클라이언트로 전달하는 것이 view파일이 아닌 데이터를 직접 전달할 경우 사용하는 어노테이션
@Controller
public class BoardController {

//    @Autowired를 사용하여 BoardService 타입의 객체 생성, 스프링 프레임워크에서 관리
    @Autowired
    private BoardService boardService;

    // @RequestMapping: 클라이언트에서 접속할 URL을 지정하는 어노테이션, JSP의 @WebServlet과 비슷한 기능을 함 
    // @RequestMapping는 클래스와 메서드 모두 사용 가능
    // 메서드에 사용 시, 실제 클라이언트가 접속할 URL로 사용
    // 클래스에 사용 시, 기본 URL로 사용 됨(클래스에 지정한 url + 메서드에 지정한 url이 최종 url)
//    여러개의 URL을 처리하고자 할 경우 {"주소1", "주소2", ...} 형석으로 사용할 수 있음
    @RequestMapping({"/", "/home", "/index"})
    public String index(){
//        반환값을 사용되는 String 데이터는 실제 클라이언트에 출력될 템플릿 파일명을 지정함
//        resource/templates 아래에 있는 템플릿 파일을 지칭함
//        영문 대소문자를 정확하게 입력해야 함
//        확장자명은 필요없음

        // 아래 return의 값 == templates(기본 설정된 파일)의 파일명
        // 만약 templates 폴더 안에 다른 폴더 안에 파일이 들어 있다면 폴더명/파일명 이렇게 써줘야 함
        return "index";
    }

//    value: URL을 입력, RequestMapping에 URL만 입력 시 value 생략 가능
//    method: 클라이언트의 데이터 전달 방식을 지정할 경우 사용(GET, POST, PUT, DELETE)
    @RequestMapping(value = "/board/boardList.do", method = RequestMethod.GET)
    public ModelAndView boardList() throws Exception {
//        ModelAndView: 클라이언트에서 화면에 출력되는 view 와 데이터가 함께 포함된 클래스
//        해당 클래스 생성자에 view 파일을 지정
        ModelAndView mv = new ModelAndView("board/boardList");

//        BoardService에서 제공하는 selectBoardList() 메서드를 사용하여 List<BoardDTO> 타입의 데이터를 가져옴
        List<BoardDTO> boardList = boardService.selectBoardList();
//        ModelAndView 클래스 타입의 객체에 'boardList'라는 이름으로 데이터를 추가
//        JSP Servlet에서 request영역에 setAttribute()를 사용하여 데이터를 추가하는 것과 같음
        mv.addObject("boardList", boardList);

//        클라이언트에게 View 템플릿 파일과 데이터를 함께 전달
        return mv;
    }
}
