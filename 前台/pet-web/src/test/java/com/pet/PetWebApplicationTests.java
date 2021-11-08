package com.pet;

import com.pet.mapper.UserMapper;
import com.pet.pojo.User;
import com.pet.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PetWebApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {

        List<User> users = userMapper.selectAll();
        for (User user : users) {
            System.out.println(user);
        }

    }

}
