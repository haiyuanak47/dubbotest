package com.lhy.provider.impl;

import com.gitee.fastmybatis.core.query.Query;
import com.lhy.common.entity.SmallQuestion;
import com.lhy.common.service.SmallQuestionService;
import com.lhy.common.service.SmallQuestionService;
import com.lhy.provider.mapper.SmallQuestionMapper;
import com.lhy.provider.mapper.SmallQuestionMapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SmallQuestionServiceImpl implements SmallQuestionService {

    @Autowired
    private SmallQuestionMapper mapper;

    @Override
    public SmallQuestion getSmallQuestion(long id) {
        Query query = new Query();
        query.eq("id", id);
        return mapper.getByQuery(query);
    }

    @Override
    public List<SmallQuestion> getListByPid(int pid){
        Query query = new Query();
        query.eq("pid", pid);
        return mapper.list(query);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public int save(SmallQuestion mSmallQuestion) {
        return mapper.save(mSmallQuestion);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(SmallQuestion mSmallQuestion) {
        return mapper.update(mSmallQuestion);
    }


}
