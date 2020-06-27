package ndk.utils_android19.from_shc;

import android.content.Context;
import android.webkit.JavascriptInterface;

public class WebAppInterface {

    private Context mContext;
    private FurtherActions mfurtherActions;

    /**
     * Instantiate the interface and set the context
     */
    public WebAppInterface(Context context, FurtherActions furtherActions) {
        mContext = context;
        mfurtherActions = furtherActions;
    }

    /**
     * Show a toast from the web page
     */
    @JavascriptInterface
    public void doActions() {
        mfurtherActions.furtherActions();
    }
}
