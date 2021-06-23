package ndk.utils_android19;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import ndk.utils_android1.DkConstants;

public class SendSmsFromFast2SmsNetworkTaskWithResponseParserForDk extends SendSmsFromFast2SmsNetworkTaskWithResponseParser {

    public SendSmsFromFast2SmsNetworkTaskWithResponseParserForDk(String applicationName, Context currentActivityContext, ProgressBar progressBar, View formView) {

        super(applicationName, currentActivityContext, progressBar, formView);
    }

    @Override
    public String configureUserAuthorizationKey() {

        return DkConstants.fast2SmsAuthorizationKey;
    }
}
