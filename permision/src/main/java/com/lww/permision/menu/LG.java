package com.lww.permision.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;


public class LG implements IMenu {
    @Override
    public Intent getAppMenuIntent(Context context) {
        Intent intent = new Intent(context.getPackageName());
        ComponentName comp = new ComponentName("com.android.settings", "com.android.settings.Settings$AccessLockSummaryActivity");
        intent.setComponent(comp);
        return intent;
    }
}
