package com.qloop.orange.model;

/**
 * Created by Qloop on 2017/5/10.
 */

public interface IApplyPush {

    void applyPush(String title, String mail, String description, String nickname, String classify,
                   OnApplyPushListener onApplyPushListener);
}
