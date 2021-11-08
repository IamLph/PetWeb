package com.pet.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pet.common.Code;
import com.pet.common.PageResult;
import com.pet.common.Result;
import com.pet.mapper.ArticleMapper;
import com.pet.pojo.Article;
import com.pet.service.ArticleService;
import com.pet.util.RandomData;
import com.pet.vo.ArticleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ArticleServiceImpl implements ArticleService {


    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private RandomData randomData;


    /**
     * 获取所有宠物用品
     * @return
     */
    @Override
    public Result getAllArticles() {

        List<Article> list = this.articleMapper.selectAll();
        if(Objects.isNull(list)){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }

        // 封装Vo
        List<ArticleVo> articleVoList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ArticleVo articleVo = new ArticleVo();
            articleVo.setId(list.get(i).getId());
            articleVo.setImgUrl(list.get(i).getImg());
            articleVo.setpName(list.get(i).getName());
            articleVo.setMoney(list.get(i).getMoney());
            articleVo.setGuiGe(list.get(i).getSpec());
            articleVo.setYongFa(list.get(i).getApply());
            articleVo.setZhuYi(list.get(i).getItem());
            articleVo.setShopUrl(list.get(i).getShop());
            articleVoList.add(articleVo);
        }
        return new Result(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc(),articleVoList);
    }

    /**
     * 分页获取宠物列表
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public PageResult<Result<Object>> getPageArticle(Integer currentPage, Integer pageSize) {

        PageHelper.startPage(currentPage,pageSize);
        List<Article> list = this.articleMapper.selectAll();
        for (Article article : list) {
            System.out.println(article.getId());
        }

        // 封装Vo
        List<ArticleVo> articleVoList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ArticleVo articleVo = new ArticleVo();
            articleVo.setId(list.get(i).getId());
            articleVo.setImgUrl(list.get(i).getImg());
            articleVo.setpName(list.get(i).getName());
            articleVo.setMoney(list.get(i).getMoney());
            articleVo.setGuiGe(list.get(i).getSpec());
            articleVo.setYongFa(list.get(i).getApply());
            articleVo.setZhuYi(list.get(i).getItem());
            articleVo.setShopUrl(list.get(i).getShop());
            articleVoList.add(articleVo);
        }
        int total = this.articleMapper.getTotal();
        Result<List<ArticleVo>> listResult = new Result<>(Code.SUCCESS.getCode(), Code.SUCCESS.getDesc(), articleVoList);
        return new PageResult<Result<Object>>(currentPage,pageSize,total,listResult);
    }

    /**
     * 管理员添加用品
     * @param article
     * @return
     */
    @Override
    public Result addArticle(Article article) {
        String id = randomData.getRandomId();
        article.setId(id);
        int i = this.articleMapper.insert(article);
        if (i <= 0){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        return new Result(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc());
    }

    /**
     * 管理修改用品
     * @param article
     * @return
     */
    @Override
    public Result updateArticle(Article article) {
        int i = this.articleMapper.updateByPrimaryKeySelective(article);
        if (i <= 0){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        return new Result(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc());
    }

    /**
     * 管理员删除用品
     * @param id
     * @return
     */
    @Override
    public Result deleteArticle(String id) {
        int i = this.articleMapper.deleteByPrimaryKey(id);
        if (i <= 0){
            return new Result(Code.ERROR.getCode(),Code.ERROR.getDesc());
        }
        return new Result(Code.SUCCESS.getCode(),Code.SUCCESS.getDesc());
    }
}
