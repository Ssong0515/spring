package bitc.fullstack405.board1.dto;

import lombok.Data;

import java.util.List;

@Data
public class BoardDTO {
    private int boardIdx;
    private String title;
    private String contents;
    private String createUser;
    private String createDate;
    private String updateUser;
    private String updateDate;
    private int hitCnt;
//    업로드 된 파일 목록
    private List<BoardFileDTO> fileList;
}
