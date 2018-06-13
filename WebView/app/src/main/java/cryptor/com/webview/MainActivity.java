package cryptor.com.webview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends Activity{

    private WebView mWebView;
    String keyword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {//실행되면
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setLayout();

        new IntentIntegrator(this).initiateScan(); //qr코드 인식 바로 실행

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

        mWebView.loadUrl("https://yangseungwon.github.io/IchVoting/");
        // WebViewClient 지정
        mWebView.setWebViewClient(new WebViewClientClass() {
            @Override
            @JavascriptInterface
            public void onPageFinished(WebView view, String url){
                if(url.equals("https://yangseungwon.github.io/IchVoting/")){
                    String script = "javascript:function dataset() {"
                            + "document.getElementById('privatekey').value = '" + keyword + "';"
                            + "};"
                            + "dataset();";

                    mWebView.loadUrl(script);
                }
            }
        });
    }

    private class WebViewClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        String QR_code = result.getContents();

        if(QR_code == null){
            Toast.makeText(this,"404 NOT FOUND",Toast.LENGTH_SHORT).show();
        }else{
            keyword=QR_code;
            mWebView.loadUrl("https://yangseungwon.github.io/IchVoting/");
        }
        super.onActivityResult(requestCode,resultCode,data);
    }

    /*
     * Layout
     */
    private void setLayout(){
        mWebView = (WebView) findViewById(R.id.webview);
    }
}