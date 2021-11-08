package com.pet.mapper;

import com.pet.pojo.Pet;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface PetMapper extends Mapper<Pet> {
    @Select("select count(*) from tb_pet")
    int getTotal();
}
