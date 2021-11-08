package com.pet.service;

import com.pet.common.Result;
import com.pet.pojo.User;
import com.pet.pojo.UserInfo;

public interface UserService {

    Result<Object> login(String userName, String password);

    Result<Object> register(String userName, String passWord);

    Result<Object> getAllInfos();


    Result getUser();

    Result addUser(String userName, String passWord);

    Result updateUser(String id, String userName, String passWord);

    Result deleteUser(String id);

    Result updateUserInfo(UserInfo userInfo);
}
