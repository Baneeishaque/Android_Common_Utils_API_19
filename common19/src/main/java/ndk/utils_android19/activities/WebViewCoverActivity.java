package ndk.utils_android9.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import ndk.utils_android1.ActivityUtils;
import ndk.utils_android1.LogUtils;
import ndk.utils_android9.R;

public abstract class WebViewCoverActivity extends AppCompatActivity {

    //TODO : Override Actionbar colors
    //TODO : Splash Screen

    WebView webView;
    AppCompatActivity currentActivity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        webView = findViewById(R.id.webview);
        webView.loadUrl("https://www." + configureCover() + ".in");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setAppCachePath(getCacheDir().getPath());
        webSettings.setAllowFileAccess(true);

        if (!configureUserAgent().isEmpty()) {

            webSettings.setUserAgentString(configureUserAgent());
        }
        webView.setWebViewClient(new AmazonWebViewClient());
    }

    public abstract String configureUserAgent();

    public abstract String configureCover();

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {

            webView.goBack();
            return true;
        }

        // If it wasn't the Back key or there's no web page history, bubble up to the default system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }

    public abstract String configureSameHostPointer();

    public abstract Class configureNextActivity();

    protected abstract boolean isSameHostLinkCheckEnabled();

    protected abstract String configureAnotherHostLinkPointer();

    protected abstract boolean isAnotherHostLinkCheckEnabled();

    protected abstract String configureAnotherHostPointer();

    protected abstract boolean isAnotherHostCheckEnabled();

    void gotoMaskedActivity() {

        ActivityUtils.startActivityForClass(currentActivity, configureNextActivity());
    }

    class AmazonWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            LogUtils.debug("URL : " + url);

            String tempHost = Uri.parse(url).getHost();
            LogUtils.debug("URL Host : " + tempHost);

            if (isAnotherHostCheckEnabled() && Objects.equals(tempHost, configureAnotherHostPointer()) && isAnotherHostLinkCheckEnabled() && url.contains(configureAnotherHostLinkPointer())) {

                gotoMaskedActivity();
                return true;

            } else if (isAnotherHostCheckEnabled() && Objects.equals(tempHost, configureAnotherHostPointer())) {

                gotoMaskedActivity();
                return true;

            } else if (isSameHostLinkCheckEnabled() && Objects.equals(tempHost, "www." + configureCover() + ".in") && url.contains(configureSameHostPointer())) {

                gotoMaskedActivity();
                return true;

            } else if (Objects.equals(tempHost, "www." + configureCover() + ".in")) {

                return false;

            } else {

                // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                return true;
            }
        }
    }
}
