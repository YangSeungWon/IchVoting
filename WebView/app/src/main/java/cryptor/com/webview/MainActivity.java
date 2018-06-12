package cryptor.com.webview;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity{

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setLayout();

        // 웹뷰에서 자바스크립트실행가능
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.getSettings().setLoadsImagesAutomatically(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setSupportZoom(false);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebView.getSettings().setAppCacheEnabled(false);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setAllowFileAccess(true);
        mWebView.setWebChromeClient(new WebChromeClient());


        // 구글홈페이지 지정
        mWebView.loadUrl("https://yangseungwon.github.io/IchVoting/");
        // WebViewClient 지정
        mWebView.setWebViewClient(new WebViewClientClass() {
            @Override
            @JavascriptInterface
            public void onPageFinished(WebView view, String url){
                if(url.equals("https://yangseungwon.github.io/IchVoting/")){
                    String keyword = "abcdefd";
                    String script = "javascript:function dataset() {"
                            + "document.getElementById('privatekey').value = '" + keyword + "';"
                            + "};"
                            + "dataset();";

                    mWebView.loadUrl(script);
                }
            }
        });
    }

    public void clicked() {

    }

    private class WebViewClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    /*
     * Layout
     */
    private void setLayout(){
        mWebView = (WebView) findViewById(R.id.webview);
    }
}