package com.pet.service.impl;

import com.github.pagehelper.PageHelper;
import com.pet.common.Code;
import com.pet.common.Result;
import com.pet.mapper.CommentMapper;
import com.pet.mapper.TrendMapper;
import com.pet.mapper.UserInfoMapper;
import com.pet.mapper.UserMapper;
import com.pet.pojo.Comment;
import com.pet.pojo.Trend;
import com.pet.pojo.User;
import com.pet.pojo.UserInfo;
import com.pet.service.TrendService;
import com.pet.util.RandomData;
import com.pet.vo.CommentVo;
import com.pet.vo.TrendVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TrendServiceImpl implements TrendService {

    @Autowired
    private TrendMapper trendMapper;

    @Autowired
    private RandomData randomData;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 用户发布动态
     * @param userId
     * @param trend
     * @return
     */
    @Override
    @Transactional
    public Result<Trend> createTrend(String userId, Trend trend) {

        //判断用户是否存在
        User user = this.userMapper.selectByPrimaryKey(userId);
        if (Objects.isNull(user)){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc(),"用户未登录,无法发布动态!");
        }

        String trendId = randomData.getRandomId();
        trend.setId(trendId);
        trend.setStar(0);
        trend.setUnstar(0);
        trend.setCommentNum(0);
        trend.setCreateTime(new Date());
        int i = this.trendMapper.insert(trend);
        if(i == 0){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        int j = this.trendMapper.insertUserIdAndTrendId(userId, trend.getId());
        if(j == 0){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }

        return new Result<>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),trend);
    }

    /**
     * 用户点赞动态
     * @param userId
     * @param trendId
     * @return
     */
    @Override
    @Transactional
    public Result starTrend(String userId, String trendId) {
        // 判断是否存在点赞关系
        int i = this.trendMapper.selectUserStarTrend(userId, trendId,0);
        if(i != 0){
            // 存在点赞关系 删除点赞关系 动态点赞量减一
            int j = this.trendMapper.deleteUserStarTrend(userId, trendId,0);
            if(j <= 0){
                return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
            }
            int l = this.trendMapper.minusTrendStar(trendId);
            if(l <= 0){
                return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
            }
            return new Result(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc());
        }
        // 不存在 点赞关系 添加关系 点赞量加1
        int j = this.trendMapper.insertUserIdAndStarTrendId(userId, trendId, 0);
        if(j <= 0){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        int l = this.trendMapper.plusTrendStar(trendId);
        if(l <= 0){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        return new Result(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc());
    }

    /**
     * 用户点踩动态
     * @param userId
     * @param trendId
     * @return
     */
    @Override
    public Result unstarTrend(String userId, String trendId) {
        int i = this.trendMapper.selectUserStarTrend(userId, trendId,1);
        if(i != 0){
            //存在点踩关系
            int j = this.trendMapper.deleteUserStarTrend(userId, trendId,1);
            if(j <= 0){
                return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
            }
            int l = this.trendMapper.minusTrendUnStar(trendId);
            if(l <= 0){
                return new Result(Code.ERROR.getCode(),Code.ERROR.getCode());
            }
            return new Result(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc());
        }
        int j = this.trendMapper.insertUserIdAndStarTrendId(userId, trendId, 1);
        if(j <= 0){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        int l = this.trendMapper.plusTrendUnStar(trendId);
        if(l <= 0){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        return new Result(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc());
    }

    /**
     * 用户在动态下发布评论
     * @param trendId
     * @param userId
     * @param content
     * @return
     */
    @Transactional
    @Override
    public Result createComment(String trendId, String userId, String content) {

        User user = this.userMapper.selectByPrimaryKey(userId);
        if (Objects.isNull(user)){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc(),"用户未登录,无法发布评论!");
        }

        // 新增评论  新增动态评论 新增用户评论
        String commentId = randomData.getRandomId();
        Comment comment = new Comment(commentId,content,new Date());
        // 新增评论
        int i = this.commentMapper.insert(comment);
        if(i <= 0){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        //新增动态评论
        int j = this.trendMapper.insertTrendAndComment(trendId, commentId);
        if(j <= 0){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        //新增用户评论
        int l = this.userMapper.insertUserAndComment(userId, commentId);
        if(l <= 0){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        int m = this.trendMapper.plusTrendCommentNum(trendId);
        if (m <= 0){
            return new Result<>(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        return new Result(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc());
    }

    /**
     * 获取所有动态
     * @return
     */
    @Override
    public Result getTrend() {

        // 获取所以动态集合
        List<Trend> list = this.trendMapper.selectAllTrend();
        for (Trend trend : list) {
            System.out.println(trend);
        }
        if(Objects.isNull(list) || CollectionUtils.isEmpty(list)){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        // 获取所有 动态id
        List<String> trendIds = list.stream().map(trend -> {
            return trend.getId();
        }).collect(Collectors.toList());

        System.out.println("---------------------");
        // 获取 userIds
        List<String> userIds = new ArrayList<>();
        for (String trendId : trendIds) {
            System.out.println(trendId);
            String userId = this.trendMapper.selectUserIdByTrendId(trendId);
            if (Objects.isNull(userId)){
                continue;
            }
            System.out.println(userId);
            userIds.add(userId);
        }
        


        // 获取用户信息集合
        List<UserInfo> userInfos = new ArrayList<>();
        System.out.println("------------------------");
        userIds.forEach(userId ->{
            UserInfo userInfo = this.userInfoMapper.selectUserInfoById(userId);
            System.out.println(userInfo);
            userInfos.add(userInfo);
        });

        // 封装vo

        List<TrendVo> trendVoList = new ArrayList<>();
        for(int i = 0;i < userIds.size();i++){
            TrendVo trendVo = new TrendVo();
            trendVo.setDynamicId(trendIds.get(i));
            trendVo.setMessage(list.get(i).getTitle());
            trendVo.setContent(list.get(i).getContent());
            trendVo.setZan(list.get(i).getStar());
            trendVo.setCai(list.get(i).getUnstar());
            trendVo.setComment(list.get(i).getCommentNum());
            trendVo.setTime(list.get(i).getCreateTime());
            trendVo.setpId(userIds.get(i));
            trendVo.setpName(userInfos.get(i).getName());
            trendVo.setUrl(userInfos.get(i).getHead());
            trendVo.setTurl(list.get(i).getImg());

            trendVoList.add(trendVo);
        }

        return new Result(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),trendVoList);
    }

    /**
     * 获取动态下的所有评论
     * @param trendId
     * @return
     */
    @Override
    public Result getComment(String trendId) {

        //根据trendId 查询所有的评论id
        List<String> commentIds = this.commentMapper.selectCommentIdByTrendId(trendId);
        if(Objects.isNull(commentIds)){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        for (String commentId : commentIds) {
            System.out.println(commentId);
        }

        // 根据评论id查询出userId
        List<String> userIds = commentIds.stream().map(userId -> {
            return this.commentMapper.selectUserIdByCommentId(userId);
        }).collect(Collectors.toList());

        for (String userId : userIds) {
            System.out.println(userId);
        }
        
        
        
        // 根据userIds查询出userInfos
        List<UserInfo> userInfos = userIds.stream().map(userId -> {
            return this.userInfoMapper.selectUserInfoById(userId);
        }).collect(Collectors.toList());

        for (UserInfo userInfo : userInfos) {
            System.out.println(userInfo);
        }

        // 根据commentIds 查询出comments
        List<Comment> comments = commentIds.stream().map(commentId -> {
            return this.commentMapper.selectByPrimaryKey(commentId);
        }).collect(Collectors.toList());

        for (Comment comment : comments) {
            System.out.println(comment);
        }
        
        //封装Vo
        List<CommentVo> list = new ArrayList<>();
        for(int i = 0;i < commentIds.size();i++){
            CommentVo commentVo = new CommentVo();
            commentVo.setId(comments.get(i).getId());
            commentVo.setDynamicId(trendId);
            commentVo.setImg(userInfos.get(i).getHead());
            commentVo.setUserId(userInfos.get(i).getId());
            commentVo.setUserName(userInfos.get(i).getName());
            commentVo.setContent(comments.get(i).getContent());
            list.add(commentVo);
        }

        return new Result(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),list);
    }

    @Override
    public Result getPageTrend(Integer currentPage, Integer pageSize) {

        //PageHelper.startPage(currentPage,pageSize);
        List<Trend> list = this.trendMapper.selectAllTrendByCreateTime();
        List<Trend> trendList = Arrays.asList(list.get(0), list.get(1), list.get(2));
        for (Trend trend : trendList) {
            System.out.println(trend);
        }
        if(Objects.isNull(trendList) || CollectionUtils.isEmpty(trendList)){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        // 获取所有 动态id
        List<String> trendIds = trendList.stream().map(trend -> {
            return trend.getId();
        }).collect(Collectors.toList());

        // 获取 userIds
        List<String> userIds = new ArrayList<>();
        for (String trendId : trendIds) {
            String userId = this.trendMapper.selectUserIdByTrendId(trendId);
            if (Objects.isNull(userId)){
                continue;
            }
            userIds.add(userId);
        }



        // 获取用户信息集合
        List<UserInfo> userInfos = new ArrayList<>();
        userIds.forEach(userId ->{
            UserInfo userInfo = this.userInfoMapper.selectByPrimaryKey(userId);
            userInfos.add(userInfo);
        });

        // 封装vo

        List<TrendVo> trendVoList = new ArrayList<>();
        for(int i = 0;i < userIds.size();i++){
            TrendVo trendVo = new TrendVo();
            trendVo.setDynamicId(trendIds.get(i));
            trendVo.setMessage(list.get(i).getTitle());
            trendVo.setContent(list.get(i).getContent());
            trendVo.setZan(list.get(i).getStar());
            trendVo.setCai(list.get(i).getUnstar());
            trendVo.setComment(list.get(i).getCommentNum());
            trendVo.setTime(list.get(i).getCreateTime());
            trendVo.setpId(userIds.get(i));
            trendVo.setpName(userInfos.get(i).getName());
            trendVo.setUrl(userInfos.get(i).getHead());
            trendVo.setTurl(list.get(i).getImg());

            trendVoList.add(trendVo);
        }

        return new Result(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),trendVoList);

    }

    /**
     * 管理员删除动态
     * @param id
     * @return
     */
    @Transactional
    @Override
    public Result deleteTrend(String id) {
        int i = this.trendMapper.deleteByPrimaryKey(id);
        if (i <= 0){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        //删除用户发布的动态和用户点赞的动态
        int j = this.trendMapper.deleteUserTrend(id);
        if(j <= 0){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }

        return new Result(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc());
    }
}
