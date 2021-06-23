package ndk.utils_android19;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;

import org.json.JSONException;

import ndk.utils_android1.ToastUtils1;

public abstract class SendSmsFromFast2SmsNetworkTaskWithResponseParser {

    String applicationName;
    Context currentActivityContext;
    ProgressBar progressBar;
    View formView;

    public SendSmsFromFast2SmsNetworkTaskWithResponseParser(String applicationName, Context currentActivityContext, ProgressBar progressBar, View formView) {

        this.applicationName = applicationName;
        this.currentActivityContext = currentActivityContext;
        this.progressBar = progressBar;
        this.formView = formView;
    }

    class SendSmsFromFast2SmsNetworkTaskForAuthorizationKey extends SendSmsFromFast2SmsNetworkTask {

        public SendSmsFromFast2SmsNetworkTaskForAuthorizationKey(String applicationName, String message, String mobileNumber, Context activityContext, ProgressBar progressBar, View formView, AsyncResponseJSONObject asyncResponseJSONObject) {

            super(applicationName, message, mobileNumber, activityContext, progressBar, formView, asyncResponseJSONObject);
        }

        @NonNull
        @Override
        public String configureFast2SmsAuthorizationKey() {

            return configureUserAuthorizationKey();
        }
    }

    public abstract String configureUserAuthorizationKey();

    public void parseResponseOfSendSmsFromFast2SmsNetworkTask(String message, String phoneNumber, String sendSuccessMessage, String sendFailedMessage, SendSuccessActions sendSuccessActions, SendFailedActions sendFailedActions) {

        new SendSmsFromFast2SmsNetworkTaskForAuthorizationKey(applicationName, message, phoneNumber, currentActivityContext, progressBar, formView, jsonObject -> {

            try {
                if (jsonObject.getString("return").equals("true")) {

                    ToastUtils1.longToast(currentActivityContext, sendSuccessMessage);
                    sendSuccessActions.performSendSuccessActions();

                } else {

                    ToastUtils1.longToast(currentActivityContext, sendFailedMessage);
                    sendFailedActions.performSendFailedActions();
                }

            } catch (JSONException jsonException) {

                ExceptionUtils19.handleExceptionOnGui(currentActivityContext, applicationName, jsonException);
            }
        }).execute();
    }

    public interface SendSuccessActions {

        void performSendSuccessActions();
    }

    public interface SendFailedActions {

        void performSendFailedActions();
    }
}
