package com.lhy.provider.mapper;

import com.gitee.fastmybatis.core.mapper.CrudMapper;
import com.lhy.common.entity.SmallQuestionBank;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * @Author: Chenjianyu
 * @Date: 2019/2/15 10:27 AM
 * @Description
 */
@Mapper
public interface SmallQuestionBankMapper extends CrudMapper<SmallQuestionBank, Long> {

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int save(SmallQuestionBank var1);
}
