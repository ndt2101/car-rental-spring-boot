package com.example.project.entity;

import com.example.project.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    // Mapping thông tin biến với tên cột trong Database
    @Column(name = "username")
    private String userName;

    // Nếu không đánh dấu @Column thì sẽ mapping tự động theo tên biến
//    private int some_attr;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
