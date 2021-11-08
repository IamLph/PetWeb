package com.pet.controller;

import com.pet.common.Result;
import com.pet.pojo.Medicine;
import com.pet.service.MedicineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/medicine")
@Api(tags = "宠物医疗点接口")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @GetMapping("/get_all")
    @ApiOperation(value = "获取宠物医疗点列表")
    public ResponseEntity<Result> getAllMedicines(){
        Result response = this.medicineService.getAllMedicines();
        if (response.getStatus() != 200){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "添加线下医疗点")
    @PostMapping("/add_medicine")
    public ResponseEntity<Result> addMedicine(
            @RequestBody Medicine medicine
            ){
        Result response = this.medicineService.addMedicine(medicine);
        if (response.getStatus() != 200){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "编辑线下医疗点")
    @PutMapping("/update_medicine")
    public ResponseEntity<Result> updateMedicine(
            @RequestBody Medicine medicine
    ){
        Result response =this.medicineService.updateMedicine(medicine);
        if (response.getStatus() != 200){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "删除线下医疗点")
    @DeleteMapping("/delete_medicine/{id}")
    public ResponseEntity<Result> deleteMedicine(
            @PathVariable("id") String id
    ){
        Result response = this.medicineService.deleteMedicine(id);
        if (response.getStatus() != 200){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }
}
