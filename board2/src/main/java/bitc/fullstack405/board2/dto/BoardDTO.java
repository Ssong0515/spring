package bitc.fullstack405.board2.dto;

import lombok.Data;

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
    private String deletedYn;
}
