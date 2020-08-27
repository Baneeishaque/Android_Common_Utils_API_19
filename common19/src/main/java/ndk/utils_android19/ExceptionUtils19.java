package ndk.utils_android19;

import java.util.Arrays;

import ndk.utils_android1.ExceptionUtils1;

//TODO : Change API

public class ExceptionUtils19 extends ExceptionUtils1 {

    public static String getExceptionDetailsApi19(Exception e) {

        return "Exception Message : " + e.getLocalizedMessage()
                + "\n" + "Exception Code : " + e.hashCode()
                + "\n" + "Exception Class : " + e.getClass()
                + "\n" + "Exception Cause : " + e.getCause()
                + "\n" + "Exception StackTrace : " + Arrays.toString(e.getStackTrace())
                + "\n" + "Exception Suppressed : " + Arrays.toString(e.getSuppressed())
                + "\n" + "Exception : " + e.toString();
    }
}
