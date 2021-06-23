package ndk.utils_android19;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import ndk.utils_android1.LogUtils1;
import ndk.utils_android1.ProgressBarUtils1;
import ndk.utils_android19.ExceptionUtils19;

public abstract class SendSmsFromFast2SmsNetworkTask extends AsyncTask<Void, Void, String[]> {

    String applicationName, message, mobileNumber;

    Context activityContext;

    ProgressBar progressBar;
    View formView;

    AsyncResponseJSONObject asyncResponseJSONObject;

    public SendSmsFromFast2SmsNetworkTask(String applicationName, String message, String mobileNumber, Context activityContext, ProgressBar progressBar, View formView, AsyncResponseJSONObject asyncResponseJSONObject) {

        //TODO : Check Message Length to restrict on SMS
        this.applicationName = applicationName;
        this.message = message;
        this.mobileNumber = mobileNumber;
        this.activityContext = activityContext;
        this.progressBar = progressBar;
        this.formView = formView;
        this.asyncResponseJSONObject = asyncResponseJSONObject;
    }

    @Override
    protected String[] doInBackground(Void... voids) {

        try {
            DefaultHttpClient defaultHttpClient;
            HttpPost httpPost;
            String networkActionResponse;

            defaultHttpClient = new DefaultHttpClient();
            httpPost = new HttpPost("https://www.fast2sms.com/dev/bulkV2");

            httpPost.setHeader("authorization", configureFast2SmsAuthorizationKey());
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");

            ArrayList<NameValuePair> nameValuePairs = new ArrayList<>(5);
            nameValuePairs.add(new BasicNameValuePair("authorization", configureFast2SmsAuthorizationKey()));
            nameValuePairs.add(new BasicNameValuePair("sender_id", "TXTIND"));
            nameValuePairs.add(new BasicNameValuePair("message", applicationName + " Otp : " + message));
            nameValuePairs.add(new BasicNameValuePair("route", "v3"));
            nameValuePairs.add(new BasicNameValuePair("numbers", mobileNumber));
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            ResponseHandler<String> basicResponseHandler = new BasicResponseHandler();

            networkActionResponse = defaultHttpClient.execute(httpPost, basicResponseHandler);

            return new String[]{"0", networkActionResponse};

        } catch (UnsupportedEncodingException unsupportedEncodingException) {

            return new String[]{"1", "UnsupportedEncodingException : " + unsupportedEncodingException.getLocalizedMessage()};

        } catch (ClientProtocolException clientProtocolException) {

            return new String[]{"1", "ClientProtocolException : " + clientProtocolException.getLocalizedMessage()};

        } catch (IOException ioException) {

            return new String[]{"1", "IOException : " + ioException.getLocalizedMessage()};
        }
    }

    @NonNull
    public abstract String configureFast2SmsAuthorizationKey();

    @Override
    protected void onPostExecute(String[] networkActionResponseArray) {

        ProgressBarUtils1.showProgress(false, activityContext, progressBar, formView);

        if (networkActionResponseArray[0].equals("1")) {

            LogUtils1.debugOnGui("Error : " + networkActionResponseArray[1] + ", Try again..",activityContext,applicationName);

        } else {

            try {

                asyncResponseJSONObject.processFinish(new JSONObject(networkActionResponseArray[1]));

            } catch (JSONException jsonException) {

                ExceptionUtils19.handleExceptionOnGui(activityContext,applicationName,jsonException);
            }
        }
    }

    @Override
    protected void onCancelled() {

        ProgressBarUtils1.showProgress(false, activityContext, progressBar, formView);
    }

    public interface AsyncResponseJSONObject {

        void processFinish(JSONObject jsonObject);
    }
}
