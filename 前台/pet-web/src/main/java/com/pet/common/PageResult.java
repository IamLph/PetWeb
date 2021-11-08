package com.pet.common;

import com.pet.vo.ArticleVo;

import java.util.List;

public class PageResult<T> {
    private Integer currentPage;
    private Integer pageSize;
    private Integer total;
    private Result result;

    public PageResult(Integer currentPage, Integer pageSize, int total, List<ArticleVo> articleVoList, Result<List<ArticleVo>> listResult) {
    }

    public PageResult(Integer currentPage, Integer pageSize, Integer total, Result result) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.total = total;
        this.result = result;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
