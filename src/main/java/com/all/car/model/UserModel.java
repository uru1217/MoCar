package com.all.car.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserModel {
    private int userId;
    private String name;
    private String password;
    private String email;
    private Timestamp regDate;
}
