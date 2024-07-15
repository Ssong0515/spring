package bitc.fullstack405.board2.service;

import bitc.fullstack405.board2.dto.UserDTO;
import bitc.fullstack405.board2.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int isUserInfo(String userId, String userPw) {
        return userMapper.isUserInfo(userId, userPw);
    }

    @Override
    public UserDTO getUserInfo(String userId) {
        return userMapper.getUserInfo(userId);
    }
}
