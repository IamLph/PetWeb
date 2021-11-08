package com.pet.mapper;

import com.pet.pojo.Trend;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface TrendMapper extends Mapper<Trend> {

    @Insert("insert into tb_user_trend (user_id,trend_id) values (#{userId},#{trendId})")
    int insertUserIdAndTrendId(@Param("userId") String userId,@Param("trendId") String trendId);

    @Insert("insert into tb_user_star_trend (user_id,trend_id,type) values (#{userId},#{trendId},#{type})")
    int insertUserIdAndStarTrendId(@Param("userId") String userId,@Param("trendId") String trendId,@Param("type") Integer type);

    @Select("select count(*) from tb_user_star_trend where user_id = #{userId} and trend_id = #{trendId} and type = #{type}")
    int selectUserStarTrend(@Param("userId") String userId,@Param("trendId") String trendId,@Param("type") Integer type);

    @Delete("delete from tb_user_star_trend where user_id = #{userId} and trend_id = #{trendId} and type = #{type}")
    int deleteUserStarTrend(@Param("userId") String userId,@Param("trendId") String trendId,@Param("type") Integer type);

    @Update("update tb_trend set star = star - 1 where id = #{trendId}")
    int minusTrendStar(String trendId);

    @Update("update tb_trend set star = star + 1 where id = #{trendId}")
    int plusTrendStar(String trendId);

    @Update("update tb_trend set unstar = unstar - 1 where id = #{trendId}")
    int minusTrendUnStar(String trendId);

    @Update("update tb_trend set unstar = unstar + 1 where id = #{trendId}")
    int plusTrendUnStar(String trendId);

    @Insert("insert into tb_trend_comment (trend_id,comment_id) values (#{trendId},#{commentId})")
    int insertTrendAndComment(@Param("trendId") String trendId,@Param("commentId") String commentId);

    @Select("select user_id from tb_user_trend where trend_id = #{trendId}")
    String selectUserIdByTrendId(String trendId);

    @Select("select trend_id from tb_user_trend where user_id = #{userId}")
    List<String> selectTrendIdsByUserId(String userId);

    @Update("update tb_trend set comment_num = comment_num + 1 where id = #{trendId}")
    int plusTrendCommentNum(String trendId);

    @Delete("delete from tb_user_trend where trend_id = #{trendId}")
    int deleteUserTrend(String trendId);

    @Delete("delete from tb_user_star_trend where trend_id = #{trendId}")
    int deleteUserTrendStar(String trendId);


    @Select("select * from tb_trend order by create_time desc")
    List<Trend> selectAllTrendByCreateTime();

    @Select("select * from tb_trend")
    List<Trend> selectAllTrend();
}
