package com.lww.permisiondemo;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lww.permision.annotation.PermisionNeed;
import com.lww.permision.annotation.PermissionCancled;
import com.lww.permision.annotation.PermissionDenied;

/*import com.lww.permision.annotation.NetWorkChecked;
import com.lww.permision.annotation.PermisionNeed;
import com.lww.permision.annotation.PermissionCancled;
import com.lww.permision.annotation.PermissionDenied;*/

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @PermisionNeed(value = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA},reququectCode = 11)
    public   void call(){
        Log.e("lwww","获取到权限");
    }

    @PermissionCancled(requestCode =11)
    public  void cancle(){
        Log.e("lwww","取消权限");
    }

    @PermissionDenied(requestCode =11)
    public   void jujue(){
        Log.e("lwww","拒绝权限");
    }

    public void getpermission(View view) {
         call();
//        action1();
    }

    @NetWork
    private void action1() {
      Toast.makeText(this, "action1 做网络 请求", Toast.LENGTH_LONG).show();

    }
}