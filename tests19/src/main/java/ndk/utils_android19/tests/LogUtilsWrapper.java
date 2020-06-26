package ndk.utils_android19.tests;

import ndk.utils_android14.LogUtils;
import ndk.utils_android16.BuildConfig;

public class LogUtilsWrapper {

    private static String tag;

    LogUtilsWrapper(String _tag){
        tag=_tag;
    }

    public static void debug(String message)
    {
        LogUtils.debug(tag, message);
    }
}
