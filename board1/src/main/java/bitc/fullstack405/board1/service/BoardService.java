package bitc.fullstack405.board1.service;

import bitc.fullstack405.board1.dto.BoardDTO;
import bitc.fullstack405.board1.dto.BoardFileDTO;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

// BoardService는 인터페이스이기 때문에 사용 방법만 제공
// 해당 인터페이스 파일을 구현하는 클래스가 반드시 필요함
public interface BoardService {

    public List<BoardDTO> selectBoardList() throws Exception;

//    public void insertBoard(BoardDTO board) throws Exception;
    public void insertBoard(BoardDTO board, MultipartHttpServletRequest multipart) throws Exception;

    BoardDTO selectBoardDetail(int boardIdx) throws Exception;

    void updateBoard(BoardDTO board) throws  Exception;

    void deleteBoard(int idx) throws Exception;

    void updateHitCount(int boardIdx) throws Exception;

    BoardFileDTO selectBoardFileInfo(int fileIdx, int boardIdx) throws Exception;
}
