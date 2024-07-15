package bitc.fullstack405.board2.mapper;

import bitc.fullstack405.board2.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    int isUserInfo(String userId, String userPw);

    UserDTO getUserInfo(String userId);
}
