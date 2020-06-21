package ndk.utils_android19;

import android.Manifest;

import ndk.utils_android1.PermissionActivity;

public abstract class WriteExternalStoragePermissionActivity extends PermissionActivity {

    @Override
    public String configurePermission() {

        return Manifest.permission.WRITE_EXTERNAL_STORAGE;
    }

    @Override
    public int configurePermissionRequestCode() {

        return configureWriteExternalStoragePermissionRequestCode();
    }

    public abstract int configureWriteExternalStoragePermissionRequestCode();

    @Override
    public String configurePermissionRequiredMessage() {

        return "External Storage Write Permission is Required…";
    }
}
