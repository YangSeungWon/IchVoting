package cryptor.com.webview;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class MainActivity extends Activity {

    private WebView mwebView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mwebView.getSettings().setJavaScriptEnabled(true);
        mwebView.getSettings().setDomStorageEnabled(true);
        mwebView = (WebView) findViewById(R.id.webview);
        mwebView.setWebChromeClient(new WebChromeClient());
        mwebView.loadUrl("https://yangseungwon.github.io/IchVoting/");
    }
}
