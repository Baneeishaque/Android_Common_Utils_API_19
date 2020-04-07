package ndk.utils_android19;

import android.Manifest;

import ndk.utils_android14.PermissionActivity;

public abstract class WriteExternalStoragePermissionActivity extends PermissionActivity {

    @Override
    protected String configurePermission() {

        return Manifest.permission.WRITE_EXTERNAL_STORAGE;
    }

    @Override
    protected int configure_PERMISSION_REQUEST_CODE() {

        return configureWriteExternalStoragePermissionRequestCode();
    }

    protected abstract int configureWriteExternalStoragePermissionRequestCode();
}
