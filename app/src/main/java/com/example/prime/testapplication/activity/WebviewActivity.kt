package com.example.prime.testapplication.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.prime.testapplication.R
import kotlinx.android.synthetic.main.webview_activity.*
import com.example.prime.testapplication.R.id.webview
import android.webkit.WebResourceRequest
import android.os.Build
import android.annotation.TargetApi
import android.util.Log
import com.example.prime.testapplication.game.MainActivity
import android.content.Intent
import android.graphics.Bitmap
import com.example.prime.testapplication.R.id.webview
import com.example.prime.testapplication.R.id.webview
import org.jetbrains.anko.toast


class WebviewActivity : FragmentActivity() {

    private var url: String? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.webview_activity)

        val bundle = intent.extras
        if (bundle != null){
            url = bundle.getString("url")
        }

        webview.setLayerType(View.LAYER_TYPE_HARDWARE, null)

//        webview.settings.setSupportMultipleWindows(true)
        webview.settings.javaScriptEnabled = true
        webview.settings.javaScriptCanOpenWindowsAutomatically = true
//        webview.getSettings().setAllowFileAccess(true);
//        webview.getSettings().setAllowContentAccess(true);
        webview.settings.domStorageEnabled = true
        webview.settings.databaseEnabled = true
        webview.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        webview.settings.setAppCacheEnabled(true)

        webview.settings.loadWithOverviewMode = true
        webview.settings.useWideViewPort = true
        webview.scrollBarStyle = WebView.SCROLLBARS_OUTSIDE_OVERLAY
//        webview.isScrollbarFadingEnabled = true
        webview.webChromeClient = WebChromeClient()
        webview.webViewClient = WebViewClient()

//        webview.webViewClient = object : WebViewClient() {
//            override fun shouldOverrideUrlLoading(wView: WebView, url: String): Boolean {
//
//                if (url.indexOf("pay.piastrix.com") > -1)
//                //check if that's a url you want to load internally
//                {
//                    webview.loadUrl(url)
//                    return true
//                } else {
//                    return false //Let the system handle it
//                }
//            }
//        }

//        webview.webViewClient = object : WebViewClient() {
//            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
//                toast(url)
//                return true
//            }
//
//            //Show loader on url load
//            override fun onLoadResource(view: WebView, url: String) {}
//
//            override fun onPageFinished(view: WebView, url: String) {
//
//                try {
//
//                    if (url.contains("pay.piastrix.com")){
//                        toast("asfasgasg")
//                    }
//
//                } catch (exception: Exception) {
//                    exception.printStackTrace()
//                }
//
//            }
//        }


//        https://pay.piastrix.com/ru/pay?shop_id=159&amount=333&currency=980&description=Refill+acc+2795530&shop_order_id=60f32e05-26a4-4c7b-9da6-8b4185d4d9dd&failed_url=https%3A%2F%2Fkasino-vulkan.fun&success_url=https%3A%2F%2Fkasino-vulkan.fun&now=2018-12-12+21%3A53%3A49.73&sign=cd104c816a51a6807a01bb0c959a018547c02b09ee2eb6402ac83a135517a35e


//        webview.webViewClient = object : WebViewClient() {
//            override fun shouldOverrideUrlLoading(webView: WebView, url: String): Boolean {
//
//                webView.loadUrl(url)
//                return shouldOverrideUrlLoading(url)
//            }
//
//            @TargetApi(Build.VERSION_CODES.N)
//            override fun shouldOverrideUrlLoading(webView: WebView, request: WebResourceRequest): Boolean {
//                val uri = request.url
//                return shouldOverrideUrlLoading(uri.toString())
//            }
//
//            private fun shouldOverrideUrlLoading(url: String): Boolean {
//                Log.e("sss", "shouldOverrideUrlLoading() URL : $url")
//
//                val intent = Intent(this@WebviewActivity, WebviewActivity::class.java)
//                intent.putExtra("url", url)
//                startActivity(intent)
//
//                return true // Returning True means that application wants to leave the current WebView and handle the url itself, otherwise return false.
//            }
//        }

        webview.loadUrl(url)

    }

    @SuppressLint("MissingSuperCall")
    override fun onSaveInstanceState(outState: Bundle) {
        webview.saveState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        webview.restoreState(savedInstanceState)
    }

    override fun onBackPressed() {
        if (webview.canGoBack()) {
            webview.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
