package com.lhy.provider.impl;

import com.gitee.fastmybatis.core.query.Query;
import com.lhy.common.entity.SmallQuestionItem;
import com.lhy.common.service.SmallQuestionItemService;
import com.lhy.provider.mapper.SmallQuestionItemMapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SmallQuestionItemServiceImpl implements SmallQuestionItemService {

    @Autowired
    private SmallQuestionItemMapper mapper;

    @Override
    public SmallQuestionItem getSmallQuestionItem(long id) {
        Query query = new Query();
        query.eq("id", id);
        return mapper.getByQuery(query);
    }

    @Override
    public List<SmallQuestionItem> getListByBankId(long bankId){
        Query query = new Query();
        query.eq("bank_id", bankId);
        return mapper.list(query);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public int save(SmallQuestionItem mSmallQuestionItem) {
        return mapper.save(mSmallQuestionItem);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(SmallQuestionItem mSmallQuestionItem) {
        return mapper.update(mSmallQuestionItem);
    }

    @Override
    public List<SmallQuestionItem> getList(String type, String types) {
        Query query = new Query();
        query.eq("type", type);
        query.eq("types", types);
        return mapper.list(query);
    }

}
