package com.lhy.provider.impl;

import com.gitee.fastmybatis.core.query.Query;
import com.lhy.common.entity.SmallQuestionBank;
import com.lhy.common.service.SmallQuestionBankService;
import com.lhy.provider.mapper.SmallQuestionBankMapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SmallQuestionBankServiceImpl implements SmallQuestionBankService {

    @Autowired
    private SmallQuestionBankMapper mapper;

    @Override
    public SmallQuestionBank getSmallQuestionBank(long id) {
        Query query = new Query();
        query.eq("id", id);
        return mapper.getByQuery(query);
    }

    @Override
    public SmallQuestionBank getSmallQuestionBank(String type, String types) {
        Query query = new Query();
        query.eq("type", type);
        query.eq("types", types);
        return mapper.getByQuery(query);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int save(SmallQuestionBank mSmallQuestionBank) {
        return mapper.save(mSmallQuestionBank);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(SmallQuestionBank mSmallQuestionBank) {
        return mapper.update(mSmallQuestionBank);
    }

    @Override
    public List<SmallQuestionBank> getList() {
        Query query = new Query();
        //query.eq("type", type);
        //query.eq("types", types);
        return mapper.list(query);
    }
}
