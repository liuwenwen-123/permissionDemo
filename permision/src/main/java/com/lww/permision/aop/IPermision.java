package com.lww.permision.aop;

public   interface IPermision {
//    同意权限
    void onPermissionGranted();

// 拒绝权限 并且 选中 不在提示
      void onRermissionDenied(int code);
//       取消权限
       void  onRermissionCancle(int code);

}
