package com.lww.permision.annotation;

import com.lww.permision.utils.PermissionUtil;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PermissionCancled {
    int requestCode() default PermissionUtil.DEFAULT_CANCELED_CODE;
}
