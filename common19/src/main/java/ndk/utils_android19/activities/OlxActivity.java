package ndk.utils_android19.activities;

public abstract class OlxActivity extends WebViewCoverActivity {

    @Override
    public String configureCover() {

        return "olx";
    }

    @Override
    public boolean isSameHostLinkCheckEnabled() {

        return false;
    }

    @Override
    public String configureSameHostPointer() {

        return "";
    }

    @Override
    public boolean isAnotherHostCheckEnabled() {

        return true;
    }

    @Override
    public String configureAnotherHostPointer() {

        return "www.olx.co.za";
    }

    @Override
    public boolean isAnotherHostLinkCheckEnabled() {

        return false;
    }

    @Override
    public String configureAnotherHostLinkPointer() {

        return "";
    }

    @Override
    public String configureUserAgent() {

        return "";
    }
}
