package com.pet.mapper;

import com.pet.pojo.Article;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ArticleMapper extends Mapper<Article> {
    @Select("select count(*) from tb_article")
    int getTotal();
}
