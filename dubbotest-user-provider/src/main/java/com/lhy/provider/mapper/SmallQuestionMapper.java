package com.lhy.provider.mapper;

import com.gitee.fastmybatis.core.mapper.CrudMapper;
import com.lhy.common.entity.SmallQuestion;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: Chenjianyu
 * @Date: 2019/2/15 10:27 AM
 * @Description
 */
@Mapper
public interface SmallQuestionMapper extends CrudMapper<SmallQuestion, Long> {
}
