package com.pet.controller;

import com.pet.common.Code;
import com.pet.common.Result;
import com.pet.pojo.User;
import com.pet.pojo.UserInfo;
import com.pet.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Api(tags = "用户接口")
@RestController
@CrossOrigin
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "普通用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName",value = "用户名",required = true,dataType = "String"),
            @ApiImplicitParam(name = "passWord",value = "密码",required = true,dataType = "String")
    })
    @PostMapping("/login")
    public ResponseEntity<Object> login(
            @RequestParam(value = "userName",required = true) String userName
            ,@RequestParam(value = "passWord",required = true) String passWord){
        Result<Object> response = this.userService.login(userName,passWord);
        if (response.getStatus() != 200){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "普通用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName",value = "用户名",required = true,dataType = "String"),
            @ApiImplicitParam(name = "passWord",value = "密码",required = true,dataType = "String")
    })
    @PostMapping("/register")
    public ResponseEntity<Object> register(
            @RequestParam(value = "userName",required = true) String userName,
            @RequestParam(value = "passWord",required = true) String passWord
            ){
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(passWord)){
            return ResponseEntity.badRequest().body(new Result<>(Code.ERROR.getCode(),"参数错误"));
        }

        Result<Object> response = this.userService.register(userName, passWord);
        if (response.getStatus() != 200){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "管理员添加普通用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName",value = "用户名",required = true,dataType = "String"),
            @ApiImplicitParam(name = "passWord",value = "密码",required = true,dataType = "String")
    })
    @PostMapping("/add_user")
    public ResponseEntity<Result> addUser(
            @RequestParam("userName") String userName,
            @RequestParam("passWord") String passWord
            ){
        Result response = this.userService.addUser(userName,passWord);
        if (response.getStatus() != 200){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "管理员修改普通用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "用户id",required = true,dataType = "String"),
            @ApiImplicitParam(name = "userName",value = "用户名",required = true,dataType = "String"),
            @ApiImplicitParam(name = "passWord",value = "密码",required = true,dataType = "String")
    })
    @PutMapping("update_user")
    public ResponseEntity<Result> updateUser(
            @RequestParam(value = "id",required = true) String id,
            @RequestParam(value = "userName",required = true) String userName,
            @RequestParam(value = "passWord",required = true) String passWord
    ){
        Result response = this.userService.updateUser(id,userName,passWord);
        if (response.getStatus() != 200){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "管理员删除用户")
    @DeleteMapping("delete_user/{id}")
    public ResponseEntity<Result> deleteUser(
            @PathVariable("id") String id
    ){
        Result response = this.userService.deleteUser(id);
        if (response.getStatus() != 200){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "获取用户详细信息列表")
    @GetMapping("/get_all_info")
    public ResponseEntity<Result> getAllInfos(){
        Result response = this.userService.getAllInfos();
        if (response.getStatus() != 200){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "获取用户基本信息列表")
    @GetMapping("/get_user")
    public ResponseEntity<Result> getUser(){
        Result response = this.userService.getUser();
        if (response.getStatus() != 200){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }


    @ApiOperation(value = "管理员修改用户详细信息")
    @PutMapping("/update_user_info")
    public ResponseEntity<Result> updateUserInfo(
            @RequestParam(value = "id",required = true) String id,
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "sex",required = false) String sex,
            @RequestParam(value = "email",required = false) String email,
            @RequestParam(value = "head",required = false) String head,
            @RequestParam(value = "address",required = false) String address
    ){

        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        userInfo.setSex(Objects.isNull(sex) ? 0 : Integer.parseInt(sex));
        userInfo.setName(name);
        userInfo.setEmail(email);
        userInfo.setHead(head);
        userInfo.setAddress(address);
        Result response = this.userService.updateUserInfo(userInfo);
        if (response.getStatus() != 200){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }
}
