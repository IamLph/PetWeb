package com.pet.service;

import com.pet.common.Result;
import com.pet.pojo.Tip;

public interface TipService {
    Result getAllTips();

    Result addTip(Tip tip);

    Result updateTip(Tip tip);

    Result deleteTip(String id);
}
