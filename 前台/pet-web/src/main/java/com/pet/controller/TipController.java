package com.pet.controller;

import com.pet.common.Result;
import com.pet.pojo.Tip;
import com.pet.service.TipService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/tip")
@Api(tags = "爱宠小知识接口")
public class TipController {

    @Autowired
    private TipService tipService;

    @GetMapping("/get_all")
    @ApiOperation(value = "获取爱宠小知识列表")
    public ResponseEntity<Result> getAllTips(){
       Result response = this.tipService.getAllTips();
       if (response.getStatus() != 200){
           return ResponseEntity.badRequest().body(response);
       }
       return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "管理员添加小知识")
    @PostMapping("/add_tip")
    public ResponseEntity<Result> addTip(
            @RequestBody Tip tip
    ){
        Result response = this.tipService.addTip(tip);
        if (response.getStatus() != 200){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "管理员修改小知识")
    @PutMapping("/update_tip")
    public ResponseEntity<Result> updateTip(
            @RequestBody Tip tip
    ){
        Result response = this.tipService.updateTip(tip);
        if (response.getStatus() != 200){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @ApiOperation("管理员删除小知识")
    @DeleteMapping("/delete_tip/{id}")
    public ResponseEntity<Result> deleteTip(
            @PathVariable("id") String id
    ){
        Result response = this.tipService.deleteTip(id);
        if (response.getStatus() != 200){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }
}
