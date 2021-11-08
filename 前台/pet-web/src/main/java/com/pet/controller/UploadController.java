package com.pet.controller;

import com.pet.common.Code;
import com.pet.common.Result;
import com.pet.service.UploadService;
import com.pet.util.RandomData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("/api/image")
@Api(tags = "上传图片接口")
public class UploadController {

    @Autowired
    private RandomData randomData;

    @Autowired
    private UploadService uploadService;

    @ApiOperation(value = "图片上传")
    @PostMapping("/upload")
    public ResponseEntity<Result<String>> upload(@RequestParam("file") MultipartFile file){

        Result<String> response = this.uploadService.upload(file);
        if (response.getStatus() != 200){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }
}
