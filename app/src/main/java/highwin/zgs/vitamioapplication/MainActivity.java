package highwin.zgs.vitamioapplication;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.Random;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private String[] video = {
            "http://dlqncdn.miaopai.com/stream/MVaux41A4lkuWloBbGUGaQ__.mp4",
            "http://movie.ks.js.cn/flv/other/2014/06/20-2.flv",
            "http://movie.ks.js.cn/flv/other/1_0.mp4"
    };
    private VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Vitamio.isInitialized(this);
        setContentView(R.layout.activity_main);
        playFunction();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_STRETCH, 0);//设置视频的缩放参数,这里设置为拉伸
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_STRETCH, 0);//设置视频的缩放参数,这里设置为拉伸
        }
        super.onConfigurationChanged(newConfig);
    }


    private void playFunction() {

        String path = "";
        path = video[new Random().nextInt(video.length)];
        if ("".equals(path)) {
            Toast.makeText(this, "请填写视频的URL", Toast.LENGTH_LONG).show();
            return;
        }
        mVideoView = ((VideoView) findViewById(R.id.view));
        mVideoView.setVideoPath(path);            //设置网络地址
        //  mVideoView.setVideoURI(Uri.parse(path));  //设置本地地址
        MediaController mediaController = new MediaController(this);
        mVideoView.setMediaController(mediaController); //设置媒体控制器
        mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_STRETCH, 0);//设置视频的缩放参数,这里设置为拉伸
        mVideoView.requestFocus();
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //设置播放为正常速度
                mp.setPlaybackSpeed(1.0f);
            }
        });

        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.seekTo(0);  //转到第一帧
                mp.start();     //开始播放
            }
        });

    }
}
