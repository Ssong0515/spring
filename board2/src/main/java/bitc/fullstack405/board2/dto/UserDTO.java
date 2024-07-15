package bitc.fullstack405.board2.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String userId;
    private String userPw;
    private String userName;
    private String userEmail;
    private String createDate;
    private String updateDate;
}
