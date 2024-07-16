package bitc.fullstack405.board3.service;

import bitc.fullstack405.board3.dto.BoardDTO;

import java.util.List;

public interface BoardService {

    List<BoardDTO> selectBoardList() throws Exception;

    BoardDTO selectBoardDetail(int boardIdx);

    void insertBoard(BoardDTO board);

    void updateBoard(BoardDTO board);

    void deleteBoard(int boardIdx);
}
