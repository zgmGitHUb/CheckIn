package service.userService;

import mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import pojo.User;

/**
 * Created by user on 2017/3/4.
 */
public class UserServiceImp implements UserService {

    @Autowired
    private UserMapper userMapper;

    public void insertUser(User user) {
        userMapper.insertUser(user);
    }
}
