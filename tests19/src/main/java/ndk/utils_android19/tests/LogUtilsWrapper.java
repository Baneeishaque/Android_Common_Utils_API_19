package ndk.utils_android19.tests;

import ndk.utils_android1.LogUtils;

public class LogUtilsWrapper {

    private static String tag;

    LogUtilsWrapper(String _tag) {
        tag = _tag;
    }

    public static void debug(String message) {
        LogUtils.debug(tag, message);
    }
}
