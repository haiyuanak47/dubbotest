package com.lhy.provider.impl;

import com.gitee.fastmybatis.core.query.Query;
import com.lhy.common.entity.Customer;
import com.lhy.common.service.CustomerService;
import com.lhy.provider.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper mapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveCustomer(Customer user) {
        return mapper.save(user);
    }

    @Override
    public List<Customer> getList() {
        return mapper.list(new Query());
    }

    @Override
    public Customer getCustomer(long userId) {
        Query query = new Query();
        query.eq("id", userId);
        return mapper.getByQuery(query);
    }


}
