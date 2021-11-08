package com.pet.controller;

import com.pet.common.Code;
import com.pet.common.Result;
import com.pet.pojo.Trend;
import com.pet.service.TrendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Api(tags = "动态接口")
@RestController
@CrossOrigin
@RequestMapping("api/trend")
public class TrendController {

    @Autowired
    private TrendService trendService;

    @ApiOperation(value = "获取所有动态")
    @GetMapping("/get_trend")
    public ResponseEntity<Result> getTrend(){
        Result response = this.trendService.getTrend();
        if(response.getStatus() != 200){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "分页获取动态")
    @GetMapping("/get_page_trend")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage",value = "当前页",defaultValue = "1",dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize",value = "页面大小",defaultValue = "3",dataType = "Integer"),
    })
    public ResponseEntity<Result> getPageTrend(
            @RequestParam(value = "currentPage",defaultValue = "1") Integer currentPage,
            @RequestParam(value = "pageSize",defaultValue = "3") Integer pageSize
    ){
        Result response = this.trendService.getPageTrend(currentPage,pageSize);
        if(response.getStatus() != 200){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }


    @ApiOperation(value = "获取动态下的所有评论")
    @GetMapping("/get_comment")
    @ApiImplicitParam(name = "trendId",value = "动态id",required = true,dataType = "String")
    public ResponseEntity<Result> getComment(
            @RequestParam(value = "trendId",required = true) String trendId
    ){
        Result response = this.trendService.getComment(trendId);
        if(response.getStatus() != 200){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }


    @ApiOperation(value = "用户发布动态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "动态发布者id",required = true,dataType = "String"),
            @ApiImplicitParam(name = "title",value = "动态标题",required = true,dataType = "String"),
            @ApiImplicitParam(name = "img",value = "动态图片",required = true,dataType = "String"),
            @ApiImplicitParam(name = "content",value = "动态内容",required = true,dataType = "String")
    })
    @PostMapping("/create_trend")
    public ResponseEntity<Result> createTrend(
            @RequestParam(value = "userId",required = false) String userId,
            @RequestParam(value = "title",required = false) String title,
            @RequestParam(value = "img",required = false) String img,
            @RequestParam(value = "content",required = false) String content

    ){
        Trend trend = new Trend(title,img,content);
        Result response =  this.trendService.createTrend(userId,trend);
        if(response.getStatus() != 200){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }


    @ApiOperation(value = "用户点赞动态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "点赞者id",required = true,dataType = "String"),
            @ApiImplicitParam(name = "trendId",value = "动态id",required = true,dataType = "String"),
    })
    @PostMapping("/star_trend")
    public ResponseEntity<Result> starTrend(
            @RequestParam(value = "userId",required = true) String userId,
            @RequestParam(value = "trendId",required = true) String trendId
    ){
        if(StringUtils.isEmpty(userId) || StringUtils.isEmpty(trendId)){
            return ResponseEntity.badRequest().body(new Result(Code.ERROR.getCode(),"点赞失败!"));
        }
        Result response = this.trendService.starTrend(userId,trendId);
        if (response.getStatus() != 200){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "用户点踩动态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "点赞者id",required = true,dataType = "String"),
            @ApiImplicitParam(name = "trendId",value = "动态id",required = true,dataType = "String"),
    })
    @PostMapping("/unstar_trend")
    public ResponseEntity<Result> unStarTrend(
            @RequestParam(value = "userId",required = true) String userId,
            @RequestParam(value = "trendId",required = true) String trendId
    ){
        if(StringUtils.isEmpty(userId) || StringUtils.isEmpty(trendId)){
            return ResponseEntity.badRequest().body(new Result(Code.ERROR.getCode(),"点赞失败!"));
        }
        Result response = this.trendService.unstarTrend(userId,trendId);
        if (response.getStatus() != 200){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }


    @ApiOperation(value = "用户发布评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "发布评论者id",required = true,dataType = "String"),
            @ApiImplicitParam(name = "trendId",value = "动态id",required = true,dataType = "String"),
            @ApiImplicitParam(name = "content",value = "评论内容",required = true,dataType = "String"),
    })
    @PostMapping("/create_comment")
    public ResponseEntity<Result> createComment(
            @RequestParam(value = "trendId",required = true) String trendId,
            @RequestParam(value = "userId",required = true) String userId,
            @RequestParam(value = "content",required = true) String content

    ){
        Result response = this.trendService.createComment(trendId,userId,content);
        if (response.getStatus() != 200){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "管理员删除动态")
    @DeleteMapping("/delete_trend/{id}")
    public ResponseEntity<Result> deleteTrend(
            @PathVariable("id") String id
    ){
        Result response = this.trendService.deleteTrend(id);
        if (response.getStatus() != 200){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }
}
