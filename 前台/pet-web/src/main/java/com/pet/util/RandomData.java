package com.pet.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomData {

    // 生成8位随机数id
    public String getRandomId(){
        Random random = new Random();
        String result="";
        for(int i=0;i<8;i++){
            //首字母不能为0
            result += (random.nextInt(9)+1);
        }
        return result;
    }

    //格式化日期

}
