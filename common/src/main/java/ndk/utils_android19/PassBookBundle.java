package ndk.utils_android19;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import androidx.core.util.Pair;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import ndk.utils_android14.PermissionAcceptedActions;
import ndk.utils_android14.PermissionActivity;
import ndk.utils_android14.PermissionDeniedActions;
import ndk.utils_android14.PermissionGrantedActions;
import ndk.utils_android16.Snackbar_Utils;
import ndk.utils_android16.models.sortable_tableView.pass_book.Pass_Book_Entry_v2;
import ndk.utils_android16.network_task.Load_Pass_Book_Task;
import ndk.utils_android16.widgets.pass_book.Pass_Book_TableView;
import ndk.utils_android16.widgets.pass_book.Pass_Book_TableView_v2;

import static ndk.utils_android16.Pdf_Utils.prompt_For_Next_Action_After_Creation;
import static ndk.utils_android16.ProgressBar_Utils.showProgress;
import static ndk.utils_android19.PassBookUtils.createPassBookPdf;

//TODO: Use new code structure
//TODO: Adjust the width of Pass Book fields in pdf

public abstract class PassBookBundle extends PermissionActivity {

    private ProgressBar progressBar;
    private Pass_Book_TableView passBookTableView;
    private Pass_Book_TableView_v2 passBookTableViewV2;
    private Load_Pass_Book_Task loadPassBookTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if (getIntent().getStringExtra("V2_FLAG") == null) {
            setContentView(R.layout.pass_book);
        } else {
            setContentView(R.layout.pass_book_v2);
        }

        initView();

        if (loadPassBookTask != null) {
            loadPassBookTask.cancel(true);
            loadPassBookTask = null;
        }

        if (getIntent().getStringExtra("V2_FLAG") == null) {

            showProgress(true, this, progressBar, passBookTableView);
            loadPassBookTask = new Load_Pass_Book_Task(getIntent().getStringExtra("URL"), this, progressBar, passBookTableView, getIntent().getStringExtra("application_name"), passBookTableView, new Pair[]{new Pair<>("user_id", getIntent().getStringExtra("user_id"))});

        } else {

            showProgress(true, this, progressBar, passBookTableViewV2);

            if (getIntent().getStringExtra("SORT_FLAG") == null) {
                loadPassBookTask = new Load_Pass_Book_Task(getIntent().getStringExtra("URL"), this, progressBar, passBookTableViewV2, getIntent().getStringExtra("application_name"), passBookTableViewV2, getIntent().getStringExtra("V2_FLAG"));
            } else {
                loadPassBookTask = new Load_Pass_Book_Task(getIntent().getStringExtra("URL"), this, progressBar, passBookTableViewV2, getIntent().getStringExtra("application_name"), passBookTableViewV2, getIntent().getStringExtra("V2_FLAG"), true);
            }

            passBookTableViewV2.SetOnRowLongClickListener(new Pass_Book_TableView_v2.OnRowLongClickListener() {
                @Override
                public void onRowLongClick(Pass_Book_Entry_v2 clickedData) {
                    Log.d(getIntent().getStringExtra("application_name"), "From Activity : " + clickedData.toString());
                    configure_ROW_LONG_CLICK_ACTIONS(clickedData);
                }
            });
        }

        loadPassBookTask.execute();

    }

    protected abstract void configure_ROW_LONG_CLICK_ACTIONS(Pass_Book_Entry_v2 clickedData);

    private void initView() {
        progressBar = findViewById(R.id.login_progress);
        if (getIntent().getStringExtra("V2_FLAG") == null) {
            passBookTableView = findViewById(R.id.tableView);
        } else {
            passBookTableViewV2 = findViewById(R.id.tableView);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pass_book, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        final String currentTimeStamp = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());
        final File passBookPdf = new File(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), getIntent().getStringExtra("application_name")) + "/Pass_Book_" + currentTimeStamp + ".pdf");

        if (id == R.id.menu_item_save) {
            getRuntimePermission(new PermissionGrantedActions() {
                @Override
                public void configurePermissionGrantedActions() {
                    writePassBookPdf(activityContext, passBookPdf, getIntent().getStringExtra("application_name"), currentTimeStamp);
                }
            }, new PermissionAcceptedActions() {
                @Override
                public void configurePermissionAcceptedActions() {
                    Snackbar_Utils.display_Short_no_FAB_success_bottom_SnackBar(activityContext, "Storage Permission Granted, Thanks...");
                    writePassBookPdf(activityContext, passBookPdf, getIntent().getStringExtra("application_name"), currentTimeStamp);
                }
            }, new PermissionDeniedActions() {
                @Override
                public void configurePermissionDeniedActions() {
                    Snackbar_Utils.display_Short_no_FAB_success_bottom_SnackBar(activityContext, "Please Allow Storage Permission...");
                }
            });
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected String configurePermission() {
        return Manifest.permission.WRITE_EXTERNAL_STORAGE;
    }

    void writePassBookPdf(Context activityContext, File passBookPdf, String applicationName, String currentTimeStamp) {
        if (createPassBookPdf(activityContext, passBookPdf, applicationName)) {
            prompt_For_Next_Action_After_Creation(this, "Pass Book Saved, What Next?", passBookPdf, getIntent().getStringExtra("application_name"), currentTimeStamp, "", "");
        }
    }

    @Override
    protected int configure_PERMISSION_REQUEST_CODE() {
        return 0;
    }
}
