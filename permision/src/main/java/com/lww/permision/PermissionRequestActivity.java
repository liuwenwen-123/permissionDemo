package com.lww.permision;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import com.lww.permision.aop.IPermision;
import com.lww.permision.utils.PermissionUtil;

public class PermissionRequestActivity extends Activity {
    private  static  String  PERMISSION="permission";
    private   static String REQUEST_CODE="code";
    private static IPermision iPermision;

    public static void startPermission(Context context, String[] permission, int code,IPermision permision) {
           iPermision=permision;
        Log.d("lww ", "PermissionRequestActivity code=="+code);
        Intent intent = new Intent(context, PermissionRequestActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);


        Bundle  bundle=new Bundle();
        bundle.putStringArray(PERMISSION,permission);
        bundle.putInt(REQUEST_CODE,code);
        intent.putExtras(bundle);
        context.startActivity(intent);
        if(context instanceof  Activity){
            ((Activity)context).overridePendingTransition(0,0);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        String[] stringArray = extras.getStringArray(PERMISSION);
        int code = extras.getInt(REQUEST_CODE);
        requestPermission(stringArray,code);
    }

    private void requestPermission(String[] permission, int code) {
        if(PermissionUtil.hasPermission(this,permission)){
//    权限通过
            iPermision.onPermissionGranted();
            finish();
        }else {
//            开始请求权限
            ActivityCompat.requestPermissions(this,permission,code);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(PermissionUtil.verifyPermission(this,grantResults)){
//             通过
            iPermision.onPermissionGranted();
        }else{
            if(PermissionUtil.shouldShowRequestPermissionRationale(this,permissions)){
//             取消
                iPermision.onRermissionCancle(requestCode);
            }else{
//                拒绝权限
                iPermision.onRermissionDenied(requestCode);
            }


        }
        finish();
        overridePendingTransition(0,0);
    }
}
