package com.example.blackbox20;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class VideoViewActivity extends AppCompatActivity {

    VideoView mVideoView = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);

        Uri uri = Uri.parse(getIntent().getStringExtra("uri"));

        mVideoView = (VideoView) findViewById(R.id.videoView);
        mVideoView.setMediaController(new MediaController(this));
        mVideoView.setVideoURI(uri);
        mVideoView.start();

        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                onBackPressed();
            }
        });
    }

}