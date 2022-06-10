package com.lww.permision.annotation;


import android.support.v4.app.INotificationSideChannel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public  @interface PermisionNeed {
    String[] value();
    int reququectCode()  default  0;

}
