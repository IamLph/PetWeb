package com.pet.mapper;

import com.pet.pojo.Medicine;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface MedicineMapper extends Mapper<Medicine> {
}
