package com.pet.mapper;

import com.pet.pojo.PetUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface PetUserMapper extends Mapper<PetUser> {

    @Delete("delete from tb_pet_user where pet_id = #{petId} and user_id = #{userId}")
    int deletePetUserByPetIdAndUserId(@Param("petId") String petId,@Param("userId") String userId);

    @Update("update tb_pet_user set user_id = #{userId},start_time = #{startTime},end_time = #{endTime},id_card = #{idCard} where pet_id = #{petId}")
    int updatePetUser(PetUser petUser);
}
