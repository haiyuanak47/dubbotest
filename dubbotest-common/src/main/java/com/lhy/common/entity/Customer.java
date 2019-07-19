package com.lhy.common.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author: Chenjianyu
 * @Date: 2019/2/14 3:40 PM
 * @Description
 */
@Table(name = "customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = -2316651973882079784L;

    //@id要写在属性上不能写在方法上
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
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
