package ndk.utils_android19.from_shc;

import android.content.Context;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import ndk.utils_android14.ActivityUtils;

public class ButtonUtils {

    public static View.OnClickListener getStartActivityButtonEvent(Context activityContext, Class targetClass) {
        return v -> ActivityUtils.startActivity(activityContext, targetClass);
    }

    public static View.OnClickListener getStartActivityWithFinishButtonEvent(Context activityContext, Class targetClass, String applicationName) {
        return v -> ActivityUtils.startActivityWithFinish(activityContext, targetClass);
    }

    public static View.OnClickListener getEmptyButtonEvent() {
        return v -> {
        };
    }

    public static View.OnClickListener getBackButtonEvent(AppCompatActivity appCompatActivity) {
        return v -> (appCompatActivity).onBackPressed();
    }

    public static View.OnClickListener getButtonEvent(FurtherActions furtherActions) {
        return v -> furtherActions.configureFurtherActions();
    }

    public interface FurtherActions {
        void configureFurtherActions();
    }
}
