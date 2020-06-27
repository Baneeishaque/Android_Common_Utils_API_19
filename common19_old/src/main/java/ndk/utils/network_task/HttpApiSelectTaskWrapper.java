package ndk.utils.network_task;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import ndk.utils.Network_Utils;
import ndk.utils.ProgressBar_Utils;
import ndk.utils.Toast_Utils;

import static ndk.utils.Network_Utils.isOnline;

public class HttpApiSelectTaskWrapper {

    public static void execute(String task_URL, Context context, View mProgressView, View mLoginFormView, String application_Name, Pair[] name_value_pairs, HttpApiSelectTask.Async_Response async_response) {

        if (isOnline(context)) {
            ProgressBar_Utils.showProgress(true, context, mProgressView, mLoginFormView);
            HttpApiSelectTask HttpApiSelectTask = new HttpApiSelectTask(task_URL, context, mProgressView, mLoginFormView, application_Name, name_value_pairs, async_response);

            HttpApiSelectTask.execute();
        } else {
            Toast_Utils.longToast(context, "Internet is unavailable");
        }
    }

    public static void execute(String task_URL, Context context, View mProgressView, View mLoginFormView, String application_Name, Pair[] name_value_pairs, HttpApiSelectTask.Async_Response_JSON_object AsyncResponseJSONObject) {

        if (isOnline(context)) {
            ProgressBar_Utils.showProgress(true, context, mProgressView, mLoginFormView);
            HttpApiSelectTask HttpApiSelectTask = new HttpApiSelectTask(task_URL, context, mProgressView, mLoginFormView, application_Name, name_value_pairs, AsyncResponseJSONObject);

            HttpApiSelectTask.execute();
        } else {
            Toast_Utils.longToast(context, "Internet is unavailable");
        }
    }

    public static void execute(String task_URL, Context context, View mProgressView, View mLoginFormView, String application_Name, Pair[] name_value_pairs, HttpApiSelectTask.Async_Response_JSON_array async_response_json_array) {

        if (isOnline(context)) {
            ProgressBar_Utils.showProgress(true, context, mProgressView, mLoginFormView);
            HttpApiSelectTask HttpApiSelectTask = new HttpApiSelectTask(task_URL, context, mProgressView, mLoginFormView, application_Name, name_value_pairs, async_response_json_array);

            HttpApiSelectTask.execute();
        } else {
            Toast_Utils.longToast(context, "Internet is unavailable");
        }
    }

    public static void execute(String task_URL, Context context, View mProgressView, View mLoginFormView, String application_Name, Pair[] name_value_pairs, HttpApiSelectTask.Async_Response_JSON_array async_response_json_array, boolean error_flag) {

        if (isOnline(context)) {
            ProgressBar_Utils.showProgress(true, context, mProgressView, mLoginFormView);
            HttpApiSelectTask HttpApiSelectTask = new HttpApiSelectTask(task_URL, context, mProgressView, mLoginFormView, application_Name, name_value_pairs, async_response_json_array, error_flag);

            HttpApiSelectTask.execute();
        } else {
            Toast_Utils.longToast(context, "Internet is unavailable");
        }
    }

    public static void execute(String task_URL, Context context, String application_Name, Pair[] name_value_pairs, HttpApiSelectTask.Async_Response_JSON_array async_response_json_array, boolean error_flag, boolean background_flag) {

        if (isOnline(context)) {

            HttpApiSelectTask HttpApiSelectTask = new HttpApiSelectTask(task_URL, context, application_Name, name_value_pairs, async_response_json_array, error_flag, background_flag);

            HttpApiSelectTask.execute();
        } else {
            if (background_flag) {
                Log.d(application_Name, "Internet is unavailable");
            } else {
                Toast_Utils.longToast(context, "Internet is unavailable");
            }
        }
    }

    public static void execute_splash(final Context context, final String task_URL, final String application_Name, final Pair[] name_value_pairs, final HttpApiSelectTask.Async_Response_JSON_array async_response_json_array) {

        if (Network_Utils.isOnline(context)) {
            HttpApiSelectTask HttpApiSelectTask = new HttpApiSelectTask(task_URL, context, application_Name, name_value_pairs, async_response_json_array);
            HttpApiSelectTask.execute();
        } else {
            View.OnClickListener retry_Failed_Network_Task = new View.OnClickListener() {
                public void onClick(View view) {
                    execute_splash(context, task_URL, application_Name, name_value_pairs, async_response_json_array);
                }
            };
            Network_Utils.display_Long_no_FAB_no_network_bottom_SnackBar(((AppCompatActivity) context).getWindow().getDecorView(), retry_Failed_Network_Task);
        }
    }

}
