package com.lww.permision.aop;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.lww.permision.PermissionRequestActivity;
import com.lww.permision.annotation.PermisionNeed;
import com.lww.permision.annotation.PermissionCancled;
import com.lww.permision.annotation.PermissionDenied;
import com.lww.permision.utils.PermissionUtil;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class PermissionApect {
    private static final String TAG = "PermissionAspect";


    @Pointcut("execution(@com.lww.permision.annotation.NetWorkChecked * *(..))")
    public void pointActionMethod() {
        Log.d("lww ", "pointActionMethod==");
    }
    @Around("pointActionMethod()")
    public  Object net(final ProceedingJoinPoint proceedingJoinPoint)throws Throwable{
        Log.d("lww ", "net==");
       boolean tedt=false;

        Object result =null;

        //        1:拿到环境

        Context context =null;

        Object aThis = proceedingJoinPoint.getThis();

        if (aThis instanceof Context) {

            context = (Context) aThis;

        }else {

            context = ((Fragment) aThis).getActivity();

        }

        if (context ==null) {

            throw new IllegalAccessException("Context is null");

        }

        if(tedt==true){

            Log.e("lww  ", "---1111-");

            result = proceedingJoinPoint.proceed();

        }else{

//            没有网络

            Toast.makeText(context,"没有网络",Toast.LENGTH_LONG).show();

        }

        return result;
    }






    //定义切面的规则
    //1.就在原来应用中哪些注释的地方放到当前切面进行处理
    //execution(注释名   注释用的地方)
    //  1、 execution( @com.dn.tim.lib_permission.annotation.Permission(切点函数)
    //              *(类名,*表示任意的类都可以使用切点函数)  *(方法名,*表示任意方法)(..(方法的参数，..表示任意参数)) )
    //  2、@annotation(permission)：传入切点函数需要传入的参数是注解类型的permission
    @Pointcut("execution(@com.lww.permision.annotation.PermisionNeed * * (..)) && @annotation(permission)")
    public  void requestPermision(PermisionNeed permission){
        Log.d(TAG,"Pointcut===>");
    }
    //2.对进入切面的内容如何处理
    //advice
    //@Before()  在切入点之前运行
    //@After()   在切入点之后运行
    //@Around()  在切入点前后都运行
    @Around("requestPermision(permission)")
    public  void  around(final  ProceedingJoinPoint point,PermisionNeed permission){
        Object object = point.getThis();
        Context context = null;
        /**
         * 兼容Fragment、Service、Activity处理
         */
        if (object instanceof Context) {
            context = (Context) object;
        }else if (object instanceof Fragment){
            context = ((Fragment)object).getActivity();
        }else if (object instanceof android.app.Fragment){
            context = ((android.app.Fragment)object).getActivity();
        }

        if (context == null || permission == null) {
            Log.d(TAG, "aroundJonitPoint error ");
            return;
        }

        final Context finalContext = context;
        PermissionRequestActivity.startPermission(context, permission.value(), permission.reququectCode(), new IPermision() {
            /**
             * 成功
             */
            @Override
            public void onPermissionGranted() {
                try {
                    Log.d(TAG,"执行===>");
                    point.proceed();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onRermissionDenied(int code) {
                boolean dispatch = PermissionUtil.invokeAnnotation(object, PermissionDenied.class, code);
                if (dispatch) {
                    PermissionUtil.goToAppMenu(finalContext);
                }
            }

            @Override
            public void onRermissionCancle(int code) {
                boolean dispatch = PermissionUtil.invokeAnnotation(object, PermissionCancled.class,code);
                if (dispatch) {
                    PermissionUtil.goToAppMenu(finalContext);
                }
            }
        });
    }

}
