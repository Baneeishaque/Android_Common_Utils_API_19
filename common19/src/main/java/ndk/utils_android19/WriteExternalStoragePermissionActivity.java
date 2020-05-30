package ndk.utils_android19;

import android.Manifest;

import ndk.utils_android1.PermissionActivity;

public abstract class WriteExternalStoragePermissionActivity extends PermissionActivity {

    @Override
    protected String configurePermission() {

        return Manifest.permission.WRITE_EXTERNAL_STORAGE;
    }

    @Override
    protected int configurePermissionRequestCode() {

        return configureWriteExternalStoragePermissionRequestCode();
    }

    protected abstract int configureWriteExternalStoragePermissionRequestCode();

    @Override
    protected String configurePermissionRequiredMessage() {

        return "External Storage Write Permission is Required…";
    }
}
