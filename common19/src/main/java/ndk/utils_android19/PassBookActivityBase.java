package ndk.utils_android19;

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
import java.util.Locale;

import ndk.utils_android16.SnackbarUtils;
import ndk.utils_android16.models.sortable_tableView.pass_book.PassBookEntryV2;
import ndk.utils_android16.network_task.LoadPassBookTask;
import ndk.utils_android16.widgets.pass_book.PassBookTableView;
import ndk.utils_android16.widgets.pass_book.PassBookTableViewV2;

import static ndk.utils_android16.Pdf_Utils.prompt_For_Next_Action_After_Creation;
import static ndk.utils_android16.ProgressBarUtils.showProgress;
import static ndk.utils_android19.PassBookUtils.createPassBookPdf;

//TODO: Use new code structure
//TODO: Adjust the width of Pass Book fields in pdf

public abstract class PassBookActivityBase extends WriteExternalStoragePermissionActivity {

    private ProgressBar progressBar;
    private PassBookTableView passBookTableView;
    private PassBookTableViewV2 passBookTableViewV2;
    private LoadPassBookTask loadPassBookTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if (configurePassBookVersion2Flag() == null) {

            setContentView(R.layout.pass_book);
            passBookTableView = findViewById(R.id.tableView);

            commonInitialization();

            showProgress(true, this, progressBar, passBookTableView);

            loadPassBookTask = new LoadPassBookTask(configurePassBookUrl(), this, progressBar, passBookTableView, configureApplicationName(), passBookTableView, new Pair[]{new Pair<>("user_id", configureUserId())});

        } else {

            setContentView(R.layout.pass_book_v2);
            passBookTableViewV2 = findViewById(R.id.tableView);

            commonInitialization();

            showProgress(true, this, progressBar, passBookTableViewV2);

            if (isSortingAvailable()) {

                loadPassBookTask = configureLoadPassBookTask();

            } else {

                loadPassBookTask = new LoadPassBookTask(configurePassBookUrl(), this, progressBar, passBookTableViewV2, configureApplicationName(), passBookTableViewV2, configureCurrentAccountId());
            }

            passBookTableViewV2.SetOnRowLongClickListener(clickedData -> {

                Log.d(configureApplicationName(), "From Activity : " + clickedData.toString());

                configure_ROW_LONG_CLICK_ACTIONS(clickedData);
            });

        }

        loadPassBookTask.execute();
    }

    public LoadPassBookTask configureLoadPassBookTask() {

        return new LoadPassBookTask(configurePassBookUrl(), this, progressBar, passBookTableViewV2, configureApplicationName(), passBookTableViewV2, configureCurrentAccountId(), true);
    }

    protected abstract String configureCurrentAccountId();

    protected abstract String configurePassBookVersion2Flag();

    protected abstract boolean isSortingAvailable();

    protected abstract String configureUserId();

    protected abstract String configureApplicationName();

    protected abstract String configurePassBookUrl();

    private void commonInitialization() {

        progressBar = findViewById(R.id.login_progress);

        if (loadPassBookTask != null) {

            loadPassBookTask.cancel(true);
            loadPassBookTask = null;
        }
    }

    protected abstract void configure_ROW_LONG_CLICK_ACTIONS(PassBookEntryV2 clickedData);

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pass_book, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will automatically handle clicks on the Home/Up button, so long as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        final String currentTimeStamp = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss", Locale.US).format(new Date());

        final File passBookPdf = new File(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), configureApplicationName()) + "/Pass_Book_" + currentTimeStamp + ".pdf");

        if (id == R.id.menu_item_save) {

            getRuntimePermission(() -> writePassBookPdf(activityContext, passBookPdf, configureApplicationName(), currentTimeStamp, configureCurrentAccountShortName(), configureCurrentAccountLongName()), () -> {

                SnackbarUtils.displayShortNoFabSuccessBottomSnackBar(activityContext, "Storage Permission Granted, Thanks…");
                writePassBookPdf(activityContext, passBookPdf, configureApplicationName(), currentTimeStamp, configureCurrentAccountShortName(), configureCurrentAccountLongName());

            }, () -> SnackbarUtils.displayShortNoFabSuccessBottomSnackBar(activityContext, "Please Allow Storage Permission…"));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected abstract String configureCurrentAccountLongName();

    protected abstract String configureCurrentAccountShortName();

    void writePassBookPdf(Context activityContext, File passBookPdf, String applicationName, String currentTimeStamp, String currentAccountShortName, String currentAccountLongName) {

        String tempCurrentAccountShortName = currentAccountShortName.isEmpty() ? "" : "\n" + currentAccountShortName;
        String tempCurrentAccountLongName = currentAccountLongName.isEmpty() ? "" : "\n" + currentAccountLongName;

        if (createPassBookPdf(activityContext, passBookPdf, applicationName, tempCurrentAccountShortName + tempCurrentAccountLongName)) {

            prompt_For_Next_Action_After_Creation(this, "Pass Book Saved, What Next?", passBookPdf, configureApplicationName(), currentTimeStamp, "", "");
        }
    }
}
