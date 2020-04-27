package com.demo.service;

import com.demo.dto.UserDto;

/**
 * @Author: LiuYong
 * @Date:2020/04/26 9:58
 * @Description: TODO 描述
 */
public interface IUserService {

    UserDto getUser(String userId);
}
