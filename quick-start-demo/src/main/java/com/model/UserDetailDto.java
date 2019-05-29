/*
 * @(#)UserDetailDto    Created on 2019/4/10
 * Copyright (c) 2019 ZDSoft Networks, Inc. All rights reserved.
 * $$ Id$$
 */
package com.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * @author wangyb
 */
public class UserDetailDto implements Serializable {
    private static final long serialVersionUID = -4910469729794962437L;

    private String name;
    private String userId;
    private String unionid;
    private Boolean active;
    private String orderInDepts;
    private Boolean isAdmin;
    private Boolean isBoss;
    private String isLeaderInDepts;
    private Boolean isHide;
    private Boolean isSenior;
    private int[] department;
    private String position;
    private String avatar;
    private Date hiredDate;
    private String jobnumber;

    public Date getHiredDate() {
        return hiredDate;
    }

    public void setHiredDate(Date hiredDate) {
        this.hiredDate = hiredDate;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getOrderInDepts() {
        return orderInDepts;
    }

    public void setOrderInDepts(String orderInDepts) {
        this.orderInDepts = orderInDepts;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Boolean getBoss() {
        return isBoss;
    }

    public void setBoss(Boolean boss) {
        isBoss = boss;
    }

    public String getIsLeaderInDepts() {
        return isLeaderInDepts;
    }

    public void setIsLeaderInDepts(String isLeaderInDepts) {
        this.isLeaderInDepts = isLeaderInDepts;
    }

    public Boolean getHide() {
        return isHide;
    }

    public void setHide(Boolean hide) {
        isHide = hide;
    }

    public Boolean getSenior() {
        return isSenior;
    }

    public void setSenior(Boolean senior) {
        isSenior = senior;
    }

    public int[] getDepartment() {
        return department;
    }

    public void setDepartment(int[] department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getJobnumber() {
        return jobnumber;
    }

    public void setJobnumber(String jobnumber) {
        this.jobnumber = jobnumber;
    }

    @Override
    public String toString() {
        return "UserDetailDto{" +
                "name='" + name + '\'' +
                ", userId='" + userId + '\'' +
                ", unionid='" + unionid + '\'' +
                ", active=" + active +
                ", orderInDepts='" + orderInDepts + '\'' +
                ", isAdmin=" + isAdmin +
                ", isBoss=" + isBoss +
                ", isLeaderInDepts='" + isLeaderInDepts + '\'' +
                ", isHide=" + isHide +
                ", isSenior=" + isSenior +
                ", department=" + Arrays.toString(department) +
                ", position='" + position + '\'' +
                ", avatar='" + avatar + '\'' +
                ", hiredDate=" + hiredDate +
                ", jobnumber='" + jobnumber + '\'' +
                '}';
    }
}
