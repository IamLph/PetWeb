package com.pet.service.impl;

import com.pet.common.Code;
import com.pet.common.Result;
import com.pet.mapper.MedicineMapper;
import com.pet.pojo.Medicine;
import com.pet.service.MedicineService;
import com.pet.util.RandomData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineMapper medicineMapper;
    
    @Autowired
    private RandomData randomData;

    @Override
    public Result getAllMedicines() {
        List<Medicine> list = this.medicineMapper.selectAll();
        if (Objects.isNull(list) || CollectionUtils.isEmpty(list)){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        return new Result(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),list);
    }

    /**
     * 管理员添加线下医疗点
     * @param medicine
     * @return
     */
    @Override
    public Result addMedicine(Medicine medicine) {

        String id = this.randomData.getRandomId();
        medicine.setId(id);
        int i = this.medicineMapper.insert(medicine);
        if(i <= 0){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        return new Result(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc());
    }

    /**
     * 管理员修改线下医疗点
     * @param medicine
     * @return
     */
    @Override
    public Result updateMedicine(Medicine medicine) {

        int i = this.medicineMapper.updateByPrimaryKeySelective(medicine);
        if(i <= 0){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        return new Result(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc());
    }

    /**
     * 管理员删除
     * @param id
     * @return
     */
    @Override
    public Result deleteMedicine(String id) {
        int i = this.medicineMapper.deleteByPrimaryKey(id);
        if(i <= 0){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        return new Result(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc());
    }
}
