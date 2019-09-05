package com.lhy.common.service;

import com.lhy.common.entity.SmallQuestionBank;

import java.util.List;

public interface SmallQuestionBankService {

    SmallQuestionBank getSmallQuestionBank(long id);

    SmallQuestionBank getSmallQuestionBank(String type, String types);

    SmallQuestionBank getSmallQuestionBank(String param);

    int save(SmallQuestionBank mSmallQuestionBank);

    int update(SmallQuestionBank mSmallQuestionBank);

    List<SmallQuestionBank> getList();

}

