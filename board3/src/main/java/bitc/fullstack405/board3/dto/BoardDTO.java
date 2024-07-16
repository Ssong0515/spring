package bitc.fullstack405.board3.dto;

import lombok.Data;

@Data
public class BoardDTO {
    private int boardIdx;
    private String title;
    private String contents;
    private String hitCnt;
    private String createUser;
    private String createDate;
    private String updateUser;
    private String updateDate;
}
