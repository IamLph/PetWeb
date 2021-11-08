package com.pet.service;

import com.pet.common.Result;
import com.pet.pojo.Trend;

public interface TrendService {
    Result createTrend(String userId, Trend trend);

    Result starTrend(String userId, String trendId);

    Result unstarTrend(String userId, String trendId);

    Result createComment(String trendId, String userId, String content);

    Result getTrend();

    Result getComment(String trendId);

    Result getPageTrend(Integer currentPage, Integer pageSize);

    Result deleteTrend(String id);
}
