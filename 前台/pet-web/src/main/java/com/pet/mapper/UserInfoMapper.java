package com.pet.mapper;

import com.pet.pojo.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserInfoMapper extends Mapper<UserInfo> {

    @Select("select * from tb_user_info where id = #{id}")
    UserInfo selectUserInfoById(@Param("id") String id);
}
