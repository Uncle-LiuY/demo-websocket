package com.demo.service.impl;

import com.demo.dto.UserDto;
import com.demo.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @Author: LiuYong
 * @Date:2020/04/26 9:59
 * @Description: TODO 描述
 */
@Service("userServiceImpl")
public class UserServiceImpl implements IUserService {

    @Override
    public UserDto getUser(String userId) {
        UserDto userDto = new UserDto();
        userDto.setUserId(userId);
        userDto.setUserName("张三"+userId);
        return userDto;
    }
}
