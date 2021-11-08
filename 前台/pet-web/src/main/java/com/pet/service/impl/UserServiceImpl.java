package com.pet.service.impl;

import com.pet.common.Code;
import com.pet.common.Result;
import com.pet.mapper.CommentMapper;
import com.pet.mapper.TrendMapper;
import com.pet.mapper.UserInfoMapper;
import com.pet.mapper.UserMapper;
import com.pet.pojo.User;
import com.pet.pojo.UserInfo;
import com.pet.service.UserService;
import com.pet.util.RandomData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RandomData randomData;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private TrendMapper trendMapper;

    @Autowired
    private CommentMapper commentMapper;

    /**
     * 用户登录
     * @param userName
     * @param password
     * @return
     */
    @Override
    public Result<Object> login(String userName, String password) {
        User user = new User(userName, password);
        User newUser = this.userMapper.selectOne(user);
        if (Objects.isNull(newUser)){
            return new Result<Object>(Code.ERROR.getCode(),"用户名或密码错误!");
        }
        return new Result<Object>(Code.SUCCESS.getCode(),"登录成功!",newUser);
    }

    /**
     * 用户注册
     * @param userName
     * @param passWord
     * @return
     */
    @Override
    public Result<Object> register(String userName, String passWord) {

        User result = this.userMapper.selectUserByUserName(userName);
        //用户名已经存在
        if(!Objects.isNull(result)){
            return new Result<Object>(Code.ERROR.getCode(),"该用户已存在");
        }
        String id = randomData.getRandomId();
        User user = new User(id,userName,passWord,0);
        int i = this.userMapper.insert(user);
        if(i == 0){
            return new Result<>(Code.ERROR.getCode(),"注册失败!");
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        userInfo.setName(userName);
        userInfo.setSex(0);
        userInfo.setHead("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3263677461,2252816345&fm=26&gp=0.jpg");
        userInfo.setEmail("xxx@qq.com");
        userInfo.setAddress("暂无");

        int j = this.userInfoMapper.insert(userInfo);
        if(j <= 0){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        return new Result<>(Code.SUCCESS.getCode(),"注册成功");
    }

    @Override
    public Result getAllInfos() {
        List<UserInfo> list = this.userInfoMapper.selectAll();
        System.out.println(list);
        if(Objects.isNull(list) || CollectionUtils.isEmpty(list)){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        return new Result(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),list);
    }
    

    /**
     * 获取用户基本信息
     * @return
     */
    @Override
    public Result getUser() {
        List<User> list = this.userMapper.selectAllUser();
        if(Objects.isNull(list) || CollectionUtils.isEmpty(list)){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        return new Result(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),list);
    }

    /**
     * 添加新用户
     * @param userName
     * @param passWord
     * @return
     */
    @Transactional
    @Override
    public Result addUser(String userName, String passWord) {
        
        User result = this.userMapper.selectUserByUserName(userName);
        //用户名已经存在
        if(!Objects.isNull(result)){
            return new Result<Object>(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        String userId = randomData.getRandomId();
        User user = new User(userId,userName,passWord,0);
        int i = this.userMapper.insert(user);
        if(i == 0){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        
        //添加用户基本信息
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userId);
        userInfo.setName("xxx");
        userInfo.setSex(0);
        userInfo.setEmail("xxx@qq.com");
        userInfo.setAddress("暂无");
        int j = this.userInfoMapper.insert(userInfo);
        if(j <= 0){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }

        return new Result(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc());
    }

    /**
     * 管理员修改用户基本信息
     * @param id
     * @param userName
     * @param passWord
     * @return
     */
    @Override
    public Result updateUser(String id, String userName, String passWord) {
        //判断该用户是否存在
        User user = this.userMapper.selectByPrimaryKey(id);
        if(Objects.isNull(user)){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        // 修改
        User newUser = new User(id,userName,passWord,0);
        int i = this.userMapper.updateByPrimaryKey(newUser);
        if(i <= 0){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        return new Result(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc());
    }

    /**
     * 管理员删除用户
     * @param id
     * @return
     */
    @Transactional
    @Override
    public Result deleteUser(String id) {
        // 判断该用户是否存在
        User user = this.userMapper.selectByPrimaryKey(id);
        if(Objects.isNull(user)){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }

        int i = this.userMapper.deleteByPrimaryKey(id);
        if(i <= 0){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        // 级联删除用户详细信息
        int j = this.userInfoMapper.deleteByPrimaryKey(id);
        if(j <= 0){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }

        // 级联删除用户动态信息

        List<String> trendIds = this.trendMapper.selectTrendIdsByUserId(user.getId());
        if (Objects.isNull(trendIds)){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }

        //删除动态信息和 动态和用户的联系
        for (String trendId : trendIds) {
            this.trendMapper.deleteByPrimaryKey(trendId);
            this.trendMapper.deleteUserTrend(trendId);
        }
        //级联删除用户评论信息

        List<String> commentIds = this.commentMapper.selectCommentIdByTrendId(user.getId());
        if (Objects.isNull(commentIds)){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }

        //删除评论信息和 联系
        for (String commentId : commentIds) {
            this.commentMapper.deleteByPrimaryKey(commentId);
            this.commentMapper.deleteCommentByCommentId(commentId);
        }

        return new Result(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc());
    }

    /**
     * 管理员修改用户详细信息
     * @param userInfo
     * @return
     */
    @Override
    public Result updateUserInfo(UserInfo userInfo) {
        // 判断该用户信息是否存在
        UserInfo newUserInfo = this.userInfoMapper.selectByPrimaryKey(userInfo.getId());
        if(Objects.isNull(newUserInfo)){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        // 修改信息
        int i = this.userInfoMapper.updateByPrimaryKeySelective(userInfo);
        if(i <= 0){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        return new Result(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc());
    }
}
