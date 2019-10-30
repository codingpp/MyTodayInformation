package com.coding.mytodayinformation;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

/**
 * 启动页
 *
 * @author codingPan
 * @date 2019-10-30
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        FullScreenVideoView vvPlay = findViewById(R.id.vvPlay);
        vvPlay.setVideoURI(Uri.parse("android.resource://" + getPackageName() + File.separator + R.raw.splash));
        vvPlay.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                //播放器准备完毕后开始播放
                mediaPlayer.start();
            }
        });
        vvPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                //播放完毕后重新开始播放
                mediaPlayer.start();
            }
        });
    }
}
