package com.demo.dto;

import java.io.Serializable;

/**
 * @Author: LiuYong
 * @Date:2020/04/26 9:57
 * @Description: TODO 描述
 */
public class UserDto implements Serializable {
    private static final long serialVersionUID = -5255173691529786212L;

    private String userId;
    private String userName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }
}
