package com.lhy.common.service;

import com.lhy.common.entity.SmallQuestionItem;

import java.util.List;

public interface SmallQuestionItemService {
    SmallQuestionItem getSmallQuestionItem(long id);

    List<SmallQuestionItem> getListByBankId(long bankId);

    int save(SmallQuestionItem mSmallQuestionItem);

    int update(SmallQuestionItem mSmallQuestionItem);

    List<SmallQuestionItem> getList(String type,String types);
	
}

