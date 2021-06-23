package ndk.utils_android19;

import android.content.Context;

import java.util.Arrays;

import ndk.utils_android1.ExceptionUtils1;

//TODO : Change API

public class ExceptionUtils19 {

    public static String getExceptionDetails(Exception e) {

        return "Exception Message : " + e.getLocalizedMessage()
                + "\n" + "Exception Code : " + e.hashCode()
                + "\n" + "Exception Class : " + e.getClass()
                + "\n" + "Exception Cause : " + e.getCause()
                + "\n" + "Exception StackTrace : " + Arrays.toString(e.getStackTrace())
                + "\n" + "Exception Suppressed : " + Arrays.toString(e.getSuppressed())
                + "\n" + "Exception : " + e;
    }

    public static void handleException(boolean isGuiPresent, Context applicationContext, final String tag, Exception exception) {

        ExceptionUtils1.handleException(isGuiPresent, applicationContext, tag, getExceptionDetails(exception));
    }

    public static void handleExceptionOnGui(Context applicationContext, final String tag, Exception exception) {

        handleException(true, applicationContext, tag, exception);
    }
}
