package com.lww.permision.menu;

import android.content.Context;
import android.content.Intent;

import com.lww.permision.utils.PermissionUtil;


public class VIVO implements IMenu {
    @Override
    public Intent getAppMenuIntent(Context context) {
        return PermissionUtil.findSystemIntent(context,"com.bairenkeji.icaller");
    }
}
