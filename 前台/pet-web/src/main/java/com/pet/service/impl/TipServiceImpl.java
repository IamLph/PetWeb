package com.pet.service.impl;

import com.pet.common.Code;
import com.pet.common.Result;
import com.pet.mapper.TipMapper;
import com.pet.pojo.Tip;
import com.pet.service.TipService;
import com.pet.util.RandomData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Service
public class TipServiceImpl implements TipService {

    @Autowired
    private TipMapper tipMapper;

    @Autowired
    private RandomData randomData;

    /**
     * 获取所有爱宠小知识
     * @return
     */
    @Override
    public Result getAllTips() {
        List<Tip> list = this.tipMapper.selectAll();
        if (Objects.isNull(list) || CollectionUtils.isEmpty(list)){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        return new Result(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),list);
    }

    /**
     * 管理员添加小知识
     * @param tip
     * @return
     */
    @Override
    public Result addTip(Tip tip) {

        String id = this.randomData.getRandomId();
        tip.setId(id);

        int i = this.tipMapper.insert(tip);
        if(i <= 0){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        return new Result(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc());
    }

    /**
     * 管理员修改小知识
     * @param tip
     * @return
     */
    @Override
    public Result updateTip(Tip tip) {
        int i = this.tipMapper.updateByPrimaryKeySelective(tip);
        if(i <= 0){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        return new Result(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc());
    }

    /**
     * 管理员删除小知识
     * @param id
     * @return
     */
    @Override
    public Result deleteTip(String id) {
        int i = this.tipMapper.deleteByPrimaryKey(id);
        if(i <= 0){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        return new Result(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc());
    }
}
