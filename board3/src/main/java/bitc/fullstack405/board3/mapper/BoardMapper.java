package bitc.fullstack405.board3.mapper;

import bitc.fullstack405.board3.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
    public List<BoardDTO> selectBoardList() throws Exception;

    BoardDTO selectBoardDetail(@Param("boardIdx") int boardIdx);

    void insertBoard(BoardDTO board);

    void updateBoard(BoardDTO board);

    void updateHitCount(@Param("boardIdx") int boardIdx);

    void deleteBoard(@Param("boardIdx") int boardIdx);

}
