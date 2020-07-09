package ndk.utils_android19;

import android.content.Context;
import android.content.Intent;

import ndk.utils_android16.Alert_Dialog_Utils;

public class ActivityUtils {

    public static void toHomeActivity(Context context) {
        context.startActivity(addCategoryToIntent(getIntentWithFlags(Intent.ACTION_MAIN, new int[]{Intent.FLAG_ACTIVITY_NEW_TASK}), new String[]{Intent.CATEGORY_HOME}));
    }

    public static Intent getIntentWithFlags(String targetActivity, int[] flags) {
        return setFlagsToIntent(new Intent(targetActivity), flags);
    }

    public static Intent setFlagsToIntent(Intent intent, int[] flags) {
        for (int flag : flags) {
            intent.setFlags(flag);
        }
        return intent;
    }

    public static Intent addCategoryToIntent(Intent intent, String[] categories) {
        for (String category : categories) {
            intent.addCategory(category);
        }
        return intent;
    }

    public static void toHomeActivityWithConfirmation(Context context) {
        new Alert_Dialog_Utils((dialog, which) -> {
            toHomeActivity(context);
        }, (dialog, which) -> {
        }).titled_Yes_No_Dialogue(context, "Do you wish to Exit?", "Caution!", true);
    }
}
