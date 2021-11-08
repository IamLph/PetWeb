package com.pet.controller;

import com.pet.common.PageResult;
import com.pet.common.Result;
import com.pet.pojo.Pet;
import com.pet.pojo.PetUser;
import com.pet.service.PetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@CrossOrigin
@RequestMapping("/api/pet")
@Api(tags = "宠物接口")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping("/get_all_pet")
    @ApiOperation(value = "获取宠物基本信息列表")
    public ResponseEntity<Result> getAllPets(){
        Result response = this.petService.getAllPets();
        if (response.getStatus() != 200){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get_page_pet")
    @ApiOperation(value = "分页获取宠物基本信息")
    public ResponseEntity<PageResult> getPagePet(
            @RequestParam(value = "currentPage",defaultValue = "1") Integer currentPage,
            @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize
    ){
        PageResult<Result<Object>> response = this.petService.getPagePet(currentPage,pageSize);
        if (response.getResult().getStatus() != 200){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "根据宠物id获取宠物信息")
    @GetMapping("/get_pet")
    public ResponseEntity<Result> getPet(
            @RequestParam(value = "id",required = true) String id
    ){
       Result response = this.petService.getPet(id);
        if (response.getStatus() != 200){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "添加共享宠物")
    /*@ApiImplicitParams({
            @ApiImplicitParam(name = "petId",value = "宠物Id",required = true,dataType = "String"),
            @ApiImplicitParam(name = "userId",value = "领养人Id",required = true,dataType = "String"),
            @ApiImplicitParam(name = "startTime",value = "领养开始时间",required = true,dataType = "Date"),
            @ApiImplicitParam(name = "endTime",value = "领养接受时间",required = true,dataType = "Date"),
            @ApiImplicitParam(name = "idCard",value = "领养人身份证号",required = true,dataType = "String")
    })*/
    @PostMapping("/insert_share_pet")
    public ResponseEntity<Result> insertSharePet(
            @RequestParam(value = "petId",required = true) String petId,
            @RequestParam(value = "userId",required = true) String userId,
            @RequestParam(value = "startTime",required = true) String startTime,
            @RequestParam(value = "endTime",required = true) String endTime,
            @RequestParam(value = "idCard",required = true) String idCard
            /*@RequestBody PetUser petUser*/
    ){
        PetUser petUser = new PetUser(petId, userId, startTime, endTime, idCard);

        Result response = this.petService.insertSharePet(petUser);
        if (response.getStatus() != 200){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get_share_pet")
    @ApiOperation(value = "获得共享宠物信息列表")
    public ResponseEntity<Result> getSharePets(){
        Result response = this.petService.getSharePets();
        if (response.getStatus() != 200){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }


    @PostMapping("/add_pet")
    @ApiOperation(value = "管理员添加宠物")
    public ResponseEntity<Result> addPet(
            @RequestBody Pet pet
            ){
        Result response = this.petService.addPet(pet);
        if (response.getStatus() != 200){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update_pet")
    @ApiOperation(value = "管理员修改宠物")
    public ResponseEntity<Result> updatePet(
            @RequestBody Pet pet
    ){
        Result response = this.petService.updatePet(pet);
        if (response.getStatus() != 200){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete_pet/{id}")
    @ApiOperation(value = "管理员删除宠物")
    public ResponseEntity<Result> deletePet(
            @PathVariable("id") String id
    ){
        Result response = this.petService.deletePet(id);
        if (response.getStatus() != 200){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "管理员修改领养信息")
    @PutMapping("/update_share_pet")
    public ResponseEntity<Result> updateSharePet(
            /*@RequestParam(value = "petId",required = false) String petId,
            @RequestParam(value = "userId",required = false) String userId,
            @RequestParam(value = "startTime",required = false) String startTime,
            @RequestParam(value = "endTime",required = false) String endTime,
            @RequestParam(value = "idCard",required = false) String idCard*/
            @RequestBody PetUser petUser
    ){
        System.out.println(petUser);
        Result response = this.petService.updateSharePet(petUser);
        if (response.getStatus() != 200){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "管理员删除共享宠物信息")
    @DeleteMapping("/delete_share_pet/{userId}/{petId}")
    public ResponseEntity<Result> deleteSharePet(
            @PathVariable("userId") String userId,
            @PathVariable("petId") String petId
    ){
        PetUser petUser = new PetUser();
        petUser.setUserId(userId);
        petUser.setPetId(petId);
        Result response = this.petService.deleteSharePet(petUser);
        if (response.getStatus() != 200){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }
}
