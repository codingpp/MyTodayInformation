package com.coding.mytodayinformation.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.coding.mytodayinformation.R

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
    }
}