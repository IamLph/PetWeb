package com.pet.controller;

import com.pet.common.PageResult;
import com.pet.common.Result;
import com.pet.pojo.Article;
import com.pet.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/article")
@Api(tags = "宠物用品接口")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @ApiOperation(value = "获取宠物用品列表")
    @GetMapping("/get_all")
    public ResponseEntity<Result> getAllArticles(){
        Result response = this.articleService.getAllArticles();
        if (response.getStatus() != 200){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "分页获取宠物用品列表")
    @GetMapping("/get_page_article")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage",value = "当前页",defaultValue = "1",dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize",value = "页面大小",defaultValue = "10",dataType = "Integer"),
    })
    public ResponseEntity<PageResult> getPageArticle(
            @RequestParam(value = "currentPage",defaultValue = "1") Integer currentPage,
            @RequestParam(value = "pageSize",defaultValue = "3") Integer pageSize
    ){
        PageResult<Result<Object>> response = this.articleService.getPageArticle(currentPage,pageSize);
        if (response.getResult().getStatus() != 200){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "管理员添加宠物用品")
    @PostMapping("/add_article")
    public ResponseEntity<Result> addArticle(
            @RequestBody Article article
    ){
        Result response = this.articleService.addArticle(article);
        if (response.getStatus() != 200){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "管理员修改用品")
    @PutMapping("/update_article")
    public ResponseEntity<Result> updateArticle(
            @RequestBody Article article
    ){
        Result response = this.articleService.updateArticle(article);
        if (response.getStatus() != 200){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "管理员删除用品")
    @DeleteMapping("/delete_article/{id}")
    public ResponseEntity<Result> updateArticle(
            @PathVariable("id") String id
    ){
        Result response = this.articleService.deleteArticle(id);
        if (response.getStatus() != 200){
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }
}
