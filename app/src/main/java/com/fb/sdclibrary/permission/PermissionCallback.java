package com.fb.sdclibrary.permission;

import java.io.Serializable;

public interface PermissionCallback extends Serializable {
    void onPermissionGranted();
}
