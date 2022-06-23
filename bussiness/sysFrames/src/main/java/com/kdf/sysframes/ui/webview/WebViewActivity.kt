package com.kdf.sysframes.ui.webview

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.kdf.hilog.HiLog
import com.kdf.sysframes.R


class WebViewActivity: AppCompatActivity() {

    private var webView: WebView? = null
    private var btnSubimt: AppCompatButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        initView()
    }

    private fun initView() {
        webView = findViewById(R.id.webView)
        btnSubimt = findViewById(R.id.btnJs)

        val webSetting = webView!!.settings
        webSetting.allowFileAccess = false
        webSetting.allowFileAccessFromFileURLs = false
        webSetting.javaScriptEnabled = true
        webSetting.javaScriptCanOpenWindowsAutomatically = true

        webView!!.addJavascriptInterface(JsInterface(),"test")
        webView!!.loadUrl("file:///android_asset/index.html")

        // 由于设置了弹窗检验调用结果,所以需要支持js对话框
        // webview只是载体，内容的渲染需要使用webviewChromClient类去实现
        // 通过设置WebChromeClient对象处理JavaScript的对话框
        // 设置响应js 的Alert()函数
        // 由于设置了弹窗检验调用结果,所以需要支持js对话框
        // webview只是载体，内容的渲染需要使用webviewChromClient类去实现
        // 通过设置WebChromeClient对象处理JavaScript的对话框
        //设置响应js 的Alert()函数
        webView!!.webChromeClient = object : WebChromeClient() {
            override fun onJsAlert(
                view: WebView,
                url: String,
                message: String,
                result: JsResult
            ): Boolean {
                val b: AlertDialog.Builder = AlertDialog.Builder(this@WebViewActivity)
                b.setTitle("Alert")
                b.setMessage(message)
                b.setPositiveButton(android.R.string.ok,
                    DialogInterface.OnClickListener { dialog, which -> result.confirm() })
                b.setCancelable(false)
                b.create().show()
                return true
            }
        }

        btnSubimt!!.setOnClickListener {
//            webView!!.evaluateJavascript("javascript: callJs()") { value ->
//                HiLog.d(value)
//            }
            webView!!.loadUrl("javascript: callJs()")
        }
    }

    inner class JsInterface {

        @JavascriptInterface
        fun toastHello(str: String) {
            Toast.makeText(this@WebViewActivity,str,Toast.LENGTH_SHORT).show()
        }

    }

}