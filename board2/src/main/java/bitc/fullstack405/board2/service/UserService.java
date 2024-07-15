package bitc.fullstack405.board2.service;

import bitc.fullstack405.board2.dto.UserDTO;

public interface UserService {
    int isUserInfo(String userId, String userPw);

    UserDTO getUserInfo(String userId);
}
