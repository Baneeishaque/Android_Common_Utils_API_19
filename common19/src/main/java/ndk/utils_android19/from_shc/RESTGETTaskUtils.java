package ndk.utils_android19.from_shc;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import ndk.utils_android16.NetworkUtils;
import ndk.utils_android16.ProgressBarUtils;
import ndk.utils_android16.ToastUtils;

import static ndk.utils_android16.NetworkUtils.isOnline;

public abstract class RESTGETTaskUtils {

    public void execute(String task_URL, Context context, View mProgressView, View mLoginFormView, RESTGETTask.Async_Response async_response) {

        if (isOnline(context)) {
            ProgressBarUtils.showProgress(true, context, mProgressView, mLoginFormView);
            RESTGETTask rest_select_task = new RESTGETTask(task_URL, context, mProgressView, mLoginFormView, configureApplicationName(), async_response);
            rest_select_task.execute();
        } else {
            ToastUtils.longToast(context, "Internet is unavailable");
        }
    }

    public abstract String configureApplicationName();

    public void execute(String task_URL, Context context, View mProgressView, View mLoginFormView, RESTGETTask.Async_Response_JSON_object async_response_json_object) {

        if (isOnline(context)) {
            ProgressBarUtils.showProgress(true, context, mProgressView, mLoginFormView);
            RESTGETTask rest_select_task = new RESTGETTask(task_URL, context, mProgressView, mLoginFormView, configureApplicationName(), async_response_json_object);
            rest_select_task.execute();
        } else {
            ToastUtils.longToast(context, "Internet is unavailable");
        }
    }

    public void execute(String task_URL, Context context, View mProgressView, View mLoginFormView, RESTGETTask.Async_Response_JSON_array async_response_json_array) {

        if (isOnline(context)) {
            ProgressBarUtils.showProgress(true, context, mProgressView, mLoginFormView);
            RESTGETTask rest_select_task = new RESTGETTask(task_URL, context, mProgressView, mLoginFormView, configureApplicationName(), async_response_json_array);
            rest_select_task.execute();
        } else {
            ToastUtils.longToast(context, "Internet is unavailable");
        }
    }

    public void execute(String task_URL, Context context, View mProgressView, View mLoginFormView, RESTGETTask.Async_Response_JSON_array async_response_json_array, boolean error_flag) {

        if (isOnline(context)) {
            ProgressBarUtils.showProgress(true, context, mProgressView, mLoginFormView);
            RESTGETTask rest_select_task = new RESTGETTask(task_URL, context, mProgressView, mLoginFormView, configureApplicationName(), async_response_json_array, error_flag);
            rest_select_task.execute();
        } else {
            ToastUtils.longToast(context, "Internet is unavailable");
        }
    }

    public void execute(String task_URL, Context context, RESTGETTask.Async_Response_JSON_array async_response_json_array, boolean error_flag, boolean background_flag) {

        if (isOnline(context)) {
            RESTGETTask rest_select_task = new RESTGETTask(task_URL, context, configureApplicationName(), async_response_json_array, error_flag, background_flag);
            rest_select_task.execute();
        } else {
            if (background_flag) {
                Log.d(configureApplicationName(), "Internet is unavailable");
            } else {
                ToastUtils.longToast(context, "Internet is unavailable");
            }
        }
    }

    public void execute_splash(final Context context, final String task_URL, final RESTGETTask.Async_Response_JSON_array async_response_json_array) {

        if (isOnline(context)) {
            RESTGETTask rest_select_task = new RESTGETTask(task_URL, context, configureApplicationName(), async_response_json_array);
            rest_select_task.execute();
        } else {
            View.OnClickListener retry_Failed_Network_Task = view -> execute_splash(context, task_URL, async_response_json_array);
            NetworkUtils.displayOfflineLongNoFabBottomSnackBar(((AppCompatActivity) context).getWindow().getDecorView(), retry_Failed_Network_Task);
        }
    }
}
