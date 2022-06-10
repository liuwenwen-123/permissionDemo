package com.lww.permision.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import com.lww.permision.BuildConfig;


public class LETV implements IMenu {

    @Override
    public Intent getAppMenuIntent(Context context) {
      Intent intent = new Intent();
       /*   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("packageName", BuildConfig.APPLICATION_ID);
        ComponentName comp = new ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.PermissionAndApps");
        intent.setComponent(comp);*/
        return intent;
    }
}
