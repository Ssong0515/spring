package bitc.fullstack405.board3.controller;

import bitc.fullstack405.board3.dto.BoardDTO;
import bitc.fullstack405.board3.service.BoardService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BoardController {
/*
RESTful : URL을 자원의 이름으로 구분하여 해당 자원의 정보를 주고 받는 방식
HTTP URI를 통해서 자원을 명시하고, HTTP Method(GET, POST, PUT, DELETE)를 통해 자원에 대한 CRUD 명령을 적용 하는 방식

URI : Uniform Resource Identifier 의 줄임말, 자원 식별자
URL : Uniform Resource Locator 의 줄임말, 자원의 위치
현재는 거의 같은 의미로 사용됨

RESTful 방식은 데이터를 전송하는 방법을 지정함
HttpMethod를 사용하여 GET(조회)<select>, POST(입력)<insert>, PUT(수정)<update>, DELETE(삭제)<delete>로 구분하여 사용함

전체 URL 설정 방법
서버URL:포트번호/기본주소/추가주소/게시물번호

게시판 목록: /board3/selectBoardList.do -> /board3 <GET방식>
게시글 작성 화면(view): /board3/writeBoard.do -> /board3/write <GET방식>
게시글 작성 처리: /board3/insertBoard.do -> /board3/write <POST방식>
게시글 상세 화면: /board3/selectBoardDetail.do -> /board3/게시글번호 <GET방식>
게시글 수정: /board3/updateBoard.do -> /board3/게시글번호 <PUT방식>
게시글 삭제: /board3/deleteBoard.do -> /board3/게시글번호 <DELETE방식>

RequestMapping 어노테이션 사용 시, URI를 value 속성을 사용하여 설정, method 속성을 추가하여 GET, POST, PUT, DELETE를 사용하여 설정
@GetMapping, @PostMapping, @PutMapping, @DeleteMapping 어노테이션을 사용하여 구분해도 상관없음

Spring Framework의 기본 설정은 GET, POST 방식만 인식함
설정 파일인 application.properties에 spring.mvc.hiddenmethod.filter,enabled=true 가 추가되어야 함

Spring Framework의 템플릿(view부분)에서 GET,POST만 인식하기 때문에 PUT, DELETE를 인식시키게 하기 위해선
    input type=hidden을 추가하고, name=_method 속성을 사용하여 데이터 전송 방식(PUT, DELETE)을 지정해야 함
 */

    @Autowired
    private BoardService boardService;

    @RequestMapping({"/", "/index"})
    public String index() {
        return "Index";
    }

    // 게시물 목록
    @RequestMapping(value = "/board3", method = RequestMethod.GET)
    public ModelAndView selectList() throws Exception {
        ModelAndView mv = new ModelAndView("board/BoardList");

        List<BoardDTO> boardList = boardService.selectBoardList();
        mv.addObject("boardList", boardList);

        return mv;
    }

    // 게시물 상세
    @RequestMapping(value = "/board3/{boardIdx}", method = RequestMethod.GET)
    public ModelAndView selectBoardDetail(@PathVariable("boardIdx") int boardIdx) throws Exception {
        ModelAndView mv = new ModelAndView("board/BoardDetail");

        BoardDTO board = boardService.selectBoardDetail(boardIdx);
        mv.addObject("board", board);

        return mv;
    }

    // 게시물 등록
    @GetMapping("/board3/write")
    public String writeBoard() throws Exception {
        return "board/BoardWrite";
    }
    
    // URL이 동일 하더라도(위 아래) method 타입이 다르면 동일한 URL 사용 가능
    // 또한 클라이언트에서 get으로 보냈느냐 post로 보냈느냐에 따라 다르게 들어감
    
    @PostMapping("/board3/write")
    public String writeBoard(BoardDTO board) throws Exception {
        boardService.insertBoard(board);
        return "redirect:/board3";
    }

    // 게시물 수정
    @PutMapping("/board3/{boardIdx}")
    public String boardUpdate(BoardDTO board) throws Exception {
        boardService.updateBoard(board);

        return "redirect:/board3";
    }

    // @PathVariable : @RequestParam과 동일한 역할을 하는 어노테이션,
    //  REST 방식 사용 시 URI에 {}로 지정해 놓은 리소스 값을 받아오는 어노테이션

    // 게시물 삭제
    @DeleteMapping("/board3/{boardIdx}")
    public String deleteBoard(@PathVariable("boardIdx") int boardIdx) throws Exception {
        boardService.deleteBoard(boardIdx);

        return "redirect:/board3";
    }

}
