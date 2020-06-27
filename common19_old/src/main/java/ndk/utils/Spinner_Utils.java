package ndk.utils;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.core.util.Pair;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import ndk.utils.network_task.HttpApiSelectTask;

import static ndk.utils.Network_Utils.isOnline;
import static ndk.utils.ProgressBar_Utils.showProgress;

public class Spinner_Utils {

    public static void attach_items_to_simple_spinner(Context context, Spinner spinner, ArrayList<String> items) {
        ArrayAdapter<String> spinner_adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, items);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinner_adapter);
    }

    public static void populate_spinner_from_json_array(int start_index, JSONArray json_array, Context context, String application_name, Spinner spinner, ArrayList<String> spinner_items, String key) {
        for (int i = start_index; i < json_array.length(); i++) {
            try {
                spinner_items.add(json_array.getJSONObject(i).getString(key));
            } catch (JSONException e) {
                Toast.makeText(context, "Error : " + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                Log.d(application_name, e.getLocalizedMessage());
            }
        }
        Spinner_Utils.attach_items_to_simple_spinner(context, spinner, spinner_items);
    }

    public static void get_json_from_network_and_populate(final Context context, View progress_Bar, View form, String URL, final String application_name, final int start_index, final Spinner spinner, final ArrayList<String> spinner_items, final String key) {

        if (isOnline(context)) {
            showProgress(true, context, progress_Bar, form);
            HttpApiSelectTask HttpApiSelectTask = new HttpApiSelectTask(URL, context, progress_Bar, form, application_name, new Pair[]{}, new HttpApiSelectTask.Async_Response_JSON_array() {

                @Override
                public void processFinish(JSONArray json_array_with_error_status) {
                    populate_spinner_from_json_array(start_index, json_array_with_error_status, context, application_name, spinner, spinner_items, key);
                }

            });
            HttpApiSelectTask.execute();
        } else {
            Toast_Utils.longToast(context, "Internet is unavailable");
        }
    }
}
