package com.lww.permision.menu;

import android.content.Context;
import android.content.Intent;

import com.lww.permision.utils.PermissionUtil;


public class COOLPAD implements IMenu {
    @Override
    public Intent getAppMenuIntent(Context context) {
        return PermissionUtil.findSystemIntent(context,"com.yulong.android.security:remote");
    }
}
