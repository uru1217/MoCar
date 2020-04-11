package com.all.car.service;

import com.all.car.model.UserModel;

public interface RegService {

    int insert(UserModel userModel);

    UserModel findByEmail(String email);

    int checkEmail(String email);
}
