package com.all.car.mapper;

import com.all.car.model.UserModel;

public interface RegMapper {
    int insert(UserModel userModel);

    UserModel findByEmail(String email);
}
