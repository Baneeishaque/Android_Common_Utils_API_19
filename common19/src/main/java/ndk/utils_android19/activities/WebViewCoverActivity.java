package ndk.utils_android19.activities;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Objects;

import ndk.utils_android1.ActivityUtils1;
import ndk.utils_android1.LogUtilsWrapper1;
import ndk.utils_android14.ActivityWithContexts14;
import ndk.utils_android19.R;

public abstract class WebViewCoverActivity extends ActivityWithContexts14 {

    //TODO : Override Actionbar colors
    //TODO : Splash Screen

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        webView = findViewById(R.id.webView);
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
        webView.setWebViewClient(new CoverWebViewClient());
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

    public abstract boolean isSameHostLinkCheckEnabled();

    public abstract String configureAnotherHostLinkPointer();

    public abstract boolean isAnotherHostLinkCheckEnabled();

    public abstract String configureAnotherHostPointer();

    public abstract boolean isAnotherHostCheckEnabled();


    class CoverWebViewClient extends WebViewClient {

        class LogUtils extends LogUtilsWrapper1 {

            @Override
            public String configureApplicationTag() {

                return configureApplicationName();
            }

            @Override
            public Context configureCurrentApplicationContext() {

                return currentApplicationContext;
            }
        }

        LogUtils logUtils = new LogUtils();

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            logUtils.debug("URL : " + url);

            String tempHost = Uri.parse(url).getHost();
            logUtils.debug("URL Host : " + tempHost);

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
                ActivityUtils1.startActivityForUrl(currentActivityContext, url);
                return true;
            }
        }

        void gotoMaskedActivity() {

            ActivityUtils1.startActivityForClass(currentActivityContext, configureNextActivity());
        }
    }

    public abstract String configureApplicationName();
}
