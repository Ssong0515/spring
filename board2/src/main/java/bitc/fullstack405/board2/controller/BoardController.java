package bitc.fullstack405.board2.controller;

import bitc.fullstack405.board2.dto.BoardDTO;
import bitc.fullstack405.board2.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/board2")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @RequestMapping({"/index", "/", "/home"})
    public String index() {
        return "/Index";
    }

    @RequestMapping("/boardList.do")
    public ModelAndView boardList() throws Exception {
        ModelAndView mv = new ModelAndView("board2/BoardList");

        List<BoardDTO> boardList = boardService.selectBoardList();
        mv.addObject("boardList", boardList);

        return mv;
    }

    @RequestMapping("/boardDetail.do")
    public ModelAndView boardDetail(@RequestParam("boardIdx") int boardIdx) throws Exception {

        ModelAndView mv = new ModelAndView("board2/BoardDetail");
        BoardDTO board = boardService.selectBoardDetail(boardIdx);
        mv.addObject("board", board);

        return mv;
    }

//    public ModelAndView boardDetail(@RequestParam("boardIdx") int boardIdx, HttpServletRequest request) throws Exception {
//        HttpSession session = request.getSession();
//
//        ModelAndView mv = new ModelAndView();
//
//        if (session.getAttribute("userId") == null) {
//            mv.setViewName("redirect:/board2/login/login.do");
//        } else {
//            mv.setViewName("board2/BoardDetail");
//            BoardDTO board = boardService.selectBoardDetail(boardIdx);
//            mv.addObject("board", board);
//        }
//
//        return mv;
//    }

}
