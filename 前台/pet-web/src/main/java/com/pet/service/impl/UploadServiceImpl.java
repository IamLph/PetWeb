package com.pet.service.impl;

import com.pet.common.Code;
import com.pet.common.Result;
import com.pet.service.UploadService;
import com.pet.util.RandomData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private RandomData randomData;
    /**
     * 图片上传
     * @param file
     * @return
     */
    @Override
    public Result<String> upload(MultipartFile file) {

        try {
            String path = "E:\\pet\\images";

            String originalFilename = file.getOriginalFilename();
            // 获取后缀名
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            System.out.println(suffix);
            String randomId = randomData.getRandomId();
            String newName = randomId + suffix;
            File file1 = new File(path,newName);
            file.transferTo(file1);


            //返回路径 ip + /image/ + 图片名 + 后缀名
            String url = "http://www.pet.com/images/"+ newName;
            return new Result<String>(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),url);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<String>(Code.ERROR.getCode(),Code.ERROR.getDesc(),"图片上传失败!");
        }
    }
}
