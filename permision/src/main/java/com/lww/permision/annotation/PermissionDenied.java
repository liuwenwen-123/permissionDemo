package com.lww.permision.annotation;

import com.lww.permision.utils.PermissionUtil;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PermissionDenied {
    int requestCode() default PermissionUtil.DEFAULT_DENIED_CODE;
}
