package ndk.utils_android19;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;

import ndk.utils_android1.ToastUtils;

public class FolderUtils {

    public static boolean createDocumentsApplicationFolder(Context context, String applicationName) {

        File documentsFolder = new File(Environment.getExternalStorageDirectory() + "/Documents");
        boolean isDocumentsFolderPresent = true;
        if (!documentsFolder.exists()) {
            isDocumentsFolderPresent = documentsFolder.mkdir();
        }
        if (isDocumentsFolderPresent) {
            File applicationFolder = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOCUMENTS), applicationName);
            boolean isApplicationFolderPresent = true;
            if (!applicationFolder.exists()) {
                isApplicationFolderPresent = applicationFolder.mkdir();
            }
            if (isApplicationFolderPresent) {
                Log.i(applicationName, "Pdf Directory created");
                return true;
            } else {
                Log.i(applicationName, "Folder Creation failure ");
                ToastUtils.longToast(context, "Folder fail");
                return false;
            }
        } else {
            Log.i(applicationName, "Folder Creation failure ");
            ToastUtils.longToast(context, "Folder fail");
            return false;
        }
    }
}
