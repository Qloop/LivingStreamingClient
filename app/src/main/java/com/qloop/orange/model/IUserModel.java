package com.qloop.orange.model;

/**
 * Created by Qloop on 2017/4/30.
 */

public interface IUserModel {

    void signUp(String userName, String email, String pwd, OnSignUpListener onSignUpListener);
}
