package com.pet.mapper;

import com.pet.pojo.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface CommentMapper extends Mapper<Comment> {

    @Select("select comment_id from tb_trend_comment where trend_id = #{trendId}")
    List<String> selectCommentIdByTrendId(String trendId);

    @Select("select user_id from tb_user_comment where comment_id = #{commentId}")
    String selectUserIdByCommentId(String commentId);

    @Select("select comment_id from tb_user_comment where user_id = #{userId}")
    List<String> selectCommentIdByUserId(String userId);

    @Delete("delete from tb_user_comment where comment_id = #{commentId}")
    int deleteCommentByCommentId(String commentId);
}
