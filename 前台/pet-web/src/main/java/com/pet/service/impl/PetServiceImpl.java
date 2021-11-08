package com.pet.service.impl;

import com.github.pagehelper.PageHelper;
import com.pet.common.Code;
import com.pet.common.PageResult;
import com.pet.common.Result;
import com.pet.mapper.PetMapper;
import com.pet.mapper.PetUserMapper;
import com.pet.pojo.Pet;
import com.pet.pojo.PetUser;
import com.pet.service.PetService;
import com.pet.util.RandomData;
import com.pet.vo.ArticleVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetMapper petMapper;

    @Autowired
    private PetUserMapper petUserMapper;

    @Autowired
    private RandomData randomData;

    /**
     * 获取宠物的基本信息
     * @return
     */
    @Override
    public Result getAllPets() {
        List<Pet> list = this.petMapper.selectAll();
        if (Objects.isNull(list) || CollectionUtils.isEmpty(list)){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        return new Result(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),list);
    }

    /**
     * 共享宠物
     * @param petUser
     * @return
     */

    @Transactional
    @Override
    public Result insertSharePet(PetUser petUser) {

        //1.添加人和宠物的关系
                //有没有添加成功？

                //是否存在该宠物
        //2.修改宠物状态
                //是否修改成功?

        // 新增共享宠物关系 修改宠物状态和当前领养人
        int i = this.petUserMapper.insert(petUser);
        System.out.println(petUser);
        if(i <= 0){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        Pet pet = this.petMapper.selectByPrimaryKey(petUser.getPetId());
        if(Objects.isNull(pet)){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        //int p = 10 / 0;

        //System.out.println(pet);
        pet.setStatus(1);
        pet.setUserId(petUser.getUserId());
        int j = this.petMapper.updateByPrimaryKey(pet);
        if(j <= 0){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }

        //System.out.println(j);
        return new Result(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc());
    }

    /**
     * 获取共享宠物信息列表
     * @return
     */
    @Override
    public Result getSharePets() {
        List<PetUser> list = this.petUserMapper.selectAll();
        if (Objects.isNull(list) || CollectionUtils.isEmpty(list)){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        return new Result(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),list);
    }

    /**
     * 根据宠物id查询宠物信息
     * @param id
     * @return
     */
    @Override
    public Result getPet(String id) {
        Pet pet = this.petMapper.selectByPrimaryKey(id);
        if (Objects.isNull(pet)){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        return new Result(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),pet);
    }

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public PageResult<Result<Object>> getPagePet(Integer currentPage, Integer pageSize) {

        PageHelper.startPage(currentPage,pageSize);
        List<Pet> list = this.petMapper.selectAll();

        int total = this.petMapper.getTotal();
        Result<List<Pet>> listResult = new Result<>(Code.SUCCESS.getCode(), Code.SUCCESS.getDesc(), list);

        return new PageResult<Result<Object>>(currentPage,pageSize,total,listResult);
    }

    /**
     * 管理员添加宠物信息
     * @param pet
     * @return
     */
    @Override
    public Result addPet(Pet pet) {

        String id = this.randomData.getRandomId();
        pet.setId(id);
        pet.setStatus(0);
        int i = this.petMapper.insert(pet);
        if(i <= 0){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        return new Result(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc());
    }

    /**
     * 管理员修改宠物
     * @param pet
     * @return
     */
    @Override
    public Result updatePet(Pet pet) {
        int i = this.petMapper.updateByPrimaryKeySelective(pet);
        if (i <= 0){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        return new Result(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc());
    }

    /**
     * 管理员删除宠物
     * @param id
     * @return
     */
    @Override
    public Result deletePet(String id) {
        int i = this.petMapper.deleteByPrimaryKey(id);
        if (i <= 0){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        return new Result(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc());
    }

    /**
     * 管理员修改共享宠物信息
     * @param petUser
     * @return
     */
    @Override
    public Result updateSharePet(PetUser petUser) {

        int i = this.petUserMapper.updatePetUser(petUser);
        System.out.println(i);
        if (i <= 0){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        return new Result(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc());
    }

    /**
     * 管理员删除共享宠物信息
     * @param petUser
     * @return
     */
    @Transactional
    @Override
    public Result deleteSharePet(PetUser petUser) {
        int i = this.petUserMapper.deletePetUserByPetIdAndUserId(petUser.getPetId(), petUser.getUserId());
        if (i <= 0){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        System.out.println(i);
        //修改宠物状态
        Pet pet = this.petMapper.selectByPrimaryKey(petUser.getPetId());
        if (Objects.isNull(pet)){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        System.out.println(pet);
        pet.setStatus(0);
        pet.setUserId(null);
        int j = this.petMapper.updateByPrimaryKeySelective(pet);
        if (j <= 0){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        return new Result(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc());
    }
}
