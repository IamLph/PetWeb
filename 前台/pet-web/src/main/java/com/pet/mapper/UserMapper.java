package com.pet.mapper;

import com.pet.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface UserMapper extends Mapper<User> {

    @Select("select * from tb_user where user_name = #{userName}")
    User selectUserByUserName(String userName);

    @Insert("insert into tb_user_comment (user_id,comment_id) values (#{userId},#{commentId})")
    int insertUserAndComment(@Param("userId") String userId,@Param("commentId") String commentId);

    @Select("select * from tb_user where power = 0")
    List<User> selectAllUser();
}
