package com.fb.sdclibrary.permission;


import com.fb.sdclibrary.utils.Utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.ArrayList;

@Aspect
public class PermissionAspect {

    @Around("execution(@com.fubang.familywell.permission.Permission * *(..)) && @annotation(permissionCheck)")
    public void aroundJoinPoint(ProceedingJoinPoint joinPoint, Permission permissionCheck) {
        try {
            ArrayList<String> permissionList = new ArrayList<>(); //未获取的权限
            String[] permissions = permissionCheck.permissions();
            for (String permission : permissions) {
                if (permission != null && !GPermission.getPermission().hasPermission(Utils.getApp(), permission)) {
                    permissionList.add(permission);
                }
            }
            if (permissionList.size() == 0){
                try {
                    joinPoint.proceed();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
                return;
            }

            // start request permission
            GPermission.getPermission()
                    .request(Utils.getApp(), permissions, new PermissionCallback() {
                        @Override
                        public void onPermissionGranted() {
                            try {
                                joinPoint.proceed();
                            } catch (Throwable throwable) {
                                throwable.printStackTrace();
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
