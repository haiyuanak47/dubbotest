package com.lhy.consumer.request;

/**
 * @Author: Chenjianyu
 * @Date: 2019/2/15 2:23 PM
 * @Description
 */
public class UserRequest {
    private long id;
    private String userName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String geCustomerName() {
        return userName;
    }

    public void seCustomerName(String userName) {
        this.userName = userName;
    }

}
