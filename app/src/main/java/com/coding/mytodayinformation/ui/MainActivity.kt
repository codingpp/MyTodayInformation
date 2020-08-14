package com.coding.mytodayinformation.ui

import android.content.Context
import android.content.Intent
import android.net.http.SslError
import android.os.Bundle
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.coding.mytodayinformation.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 主页
 * @author SunPan
 * @date 2020/8/6
 */
class MainActivity : AppCompatActivity() {

    companion object {

        /**
         * 跳转到主页
         */
        fun jump(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        webivew.loadUrl("https://www.baidu.com/")
        webivew.webViewClient=object: WebViewClient(){
            override fun onReceivedSslError(
                view: WebView?,
                handler: SslErrorHandler?,
                error: SslError?
            ) {
                handler?.proceed()
            }
        }
    }
}