package ndk.utils_android19.tests;

import ndk.utils_android16.BuildConfig;
import ndk.utils_android16.Log_Utils;

public class LogUtilsWrapper {

    private static String tag;

    LogUtilsWrapper(String _tag){
        tag=_tag;
    }

    public static void debug(String message)
    {
        Log_Utils.debug(tag, message, BuildConfig.DEBUG);
    }
}
