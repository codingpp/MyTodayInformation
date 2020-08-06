package com.coding.mytodayinformation.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.coding.mytodayinformation.R;
import com.coding.mytodayinformation.base.BaseActivity;
import com.coding.mytodayinformation.view.FullScreenVideoView;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 启动页
 *
 * @author codingpp
 * @date 2019-10-30
 */
public class SplashActivity extends BaseActivity {

    /**
     * 视频总时长，精确到秒
     */
    private int videoDuration;

    @BindView(R.id.vvPlay)
    FullScreenVideoView vvPlay;
    @BindView(R.id.tvTimer)
    TextView tvTimer;

    private MyHandler myHandler = new MyHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        vvPlay.setVideoURI(Uri.parse("android.resource://" + getPackageName() + File.separator + R.raw.splash));
        vvPlay.setOnPreparedListener(mediaPlayer -> {
            //播放器准备完毕后开始播放
            mediaPlayer.start();
            videoDuration = mediaPlayer.getDuration() / 1000;
            String timerText = getString(R.string.jump) + videoDuration;
            tvTimer.setText(timerText);
            startTimer();
        });
        vvPlay.setOnCompletionListener(mediaPlayer -> jumpToMain());
    }

    /**
     * 启动倒计时
     */
    private void startTimer() {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        Runnable runnable = () -> myHandler.sendEmptyMessage(0);
        service.scheduleAtFixedRate(runnable, 1, 1, TimeUnit.SECONDS);
    }

    /**
     * 跳转到主页
     */
    private void jumpToMain() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 设置倒计时文字
     */
    private void setTvTimerText() {
        videoDuration--;
        int count = Math.max(videoDuration, 0);
        String timerText = getString(R.string.jump) + count;
        tvTimer.setText(timerText);
    }

    @OnClick(R.id.tvTimer)
    public void onViewClicked() {
        jumpToMain();
    }

    private static class MyHandler extends Handler {

        private final WeakReference<SplashActivity> mReference;

        private MyHandler(SplashActivity activity) {
            mReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            SplashActivity activity = mReference.get();
            activity.setTvTimerText();
        }
    }
}
