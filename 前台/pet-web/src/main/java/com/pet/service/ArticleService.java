package com.pet.service;

import com.pet.common.PageResult;
import com.pet.common.Result;
import com.pet.pojo.Article;

public interface ArticleService {
    Result getAllArticles();

    PageResult<Result<Object>> getPageArticle(Integer currentPage, Integer pageSize);

    Result addArticle(Article article);

    Result updateArticle(Article article);

    Result deleteArticle(String id);
}
