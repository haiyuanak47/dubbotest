package com.lhy.common.service;

import com.lhy.common.entity.SmallQuestion;

import java.util.List;

public interface SmallQuestionService {
    SmallQuestion getSmallQuestion(long id);

    List<SmallQuestion> getListByPid(int pid);

    int save(SmallQuestion mSmallQuestion);

    int update(SmallQuestion mSmallQuestion);

	
}

