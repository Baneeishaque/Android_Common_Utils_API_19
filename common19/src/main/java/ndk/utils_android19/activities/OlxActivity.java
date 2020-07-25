package ndk.utils_android9.activities;

public abstract class OlxActivity extends WebViewCoverActivity {

    @Override
    public String configureCover() {

        return "olx";
    }

    @Override
    protected boolean isSameHostLinkCheckEnabled() {

        return false;
    }

    @Override
    public String configureSameHostPointer() {

        return "";
    }

    @Override
    protected boolean isAnotherHostCheckEnabled() {

        return true;
    }

    @Override
    protected String configureAnotherHostPointer() {

        return "www.olx.co.za";
    }

    @Override
    protected boolean isAnotherHostLinkCheckEnabled() {

        return false;
    }

    @Override
    protected String configureAnotherHostLinkPointer() {

        return "";
    }

    @Override
    public String configureUserAgent() {

        return "";
    }
}
