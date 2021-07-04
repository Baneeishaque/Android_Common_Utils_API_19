package ndk.utils_android19;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import ndk.utils_android1.ActivityUtils1;
import ndk.utils_android1.DisplayHelper;
import ndk.utils_android14.ActivityUtils14;

public class ButtonUtils19 {

    public static View.OnClickListener getStartActivityButtonEvent(Context activityContext, Class targetClass) {
        return v -> ActivityUtils1.startActivityForClass(activityContext, targetClass);
    }

    public static View.OnClickListener getStartActivityWithFinishButtonEvent(Context activityContext, Class targetClass, String applicationName) {
        return v -> ActivityUtils14.startActivityForClassWithFinish(activityContext, targetClass);
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

    public static Button createButtonWithClickEvent(Context activityContext, String text, View.OnClickListener onClickEvent) {

        //Create Button Dynamically
        Button button = new Button(activityContext);
        button.setText(text);
        button.setId(View.generateViewId());
        //TODO : Sentence case for text

        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, DisplayHelper.dpToPixel(48, activityContext));
        button.setLayoutParams(layoutParams);

        button.setOnClickListener(onClickEvent);
        return button;
    }
}
