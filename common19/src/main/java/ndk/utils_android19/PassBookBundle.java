package ndk.utils_android19;

public abstract class PassBookBundle extends PassBookActivityBase {

    @Override
    protected String configurePassBookVersion2Flag() {

        return getIntent().getStringExtra("V2_FLAG");
    }

    @Override
    protected boolean isSortingAvailable() {

        return getIntent().getStringExtra("SORT_FLAG") == null;
    }

    @Override
    protected String configureUserId() {

        return getIntent().getStringExtra("user_id");
    }

    @Override
    protected String configureApplicationName() {

        return getIntent().getStringExtra("application_name");
    }

    @Override
    protected String configurePassBookUrl() {

        return getIntent().getStringExtra("URL");
    }

    @Override
    protected String configureCurrentAccountId() {

        return getIntent().getStringExtra("current_account_id");
    }
}
