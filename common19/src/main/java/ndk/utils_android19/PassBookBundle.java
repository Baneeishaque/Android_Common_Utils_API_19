package ndk.utils_android19;

import java.util.Objects;

public abstract class PassBookBundle extends PassBookActivityBase {

    @Override
    public boolean isV2() {

        return Objects.equals(getIntent().getStringExtra("V2_FLAG"), String.valueOf(true));
    }

    @Override
    public boolean isSortingAvailable() {

        return Objects.equals(getIntent().getStringExtra("SORT_FLAG"), String.valueOf(true));
    }

    @Override
    public String configureUserId() {

        return getIntent().getStringExtra("user_id");
    }

    @Override
    public String configureApplicationName() {

        return getIntent().getStringExtra("application_name");
    }

    @Override
    public String configurePassBookUrl() {

        return getIntent().getStringExtra("URL");
    }

    @Override
    public String configureCurrentAccountId() {

        return getIntent().getStringExtra("current_account_id");
    }
}
