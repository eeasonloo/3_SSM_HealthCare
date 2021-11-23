package org.eeasonloo.pojo;

import java.io.Serializable;
import java.util.Date;

public class OrderList implements Serializable {

    private int id;
    private String memberName;
    private String memberEmail;
    private Date orderDate;
    private String orderType;
    private String orderStatus;
    private String setmealName;

    public OrderList() {
    }

    public OrderList(int id, String memberName, String memberEmail, Date orderDate, String orderType, String orderStatus, String setmealName) {
        this.id = id;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.orderDate = orderDate;
        this.orderType = orderType;
        this.orderStatus = orderStatus;
        this.setmealName = setmealName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getSetmealName() {
        return setmealName;
    }

    public void setSetmealName(String setmealName) {
        this.setmealName = setmealName;
    }
}
