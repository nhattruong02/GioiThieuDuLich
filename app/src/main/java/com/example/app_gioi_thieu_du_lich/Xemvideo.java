package com.example.app_gioi_thieu_du_lich;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class Xemvideo extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    String linkvideo;
    String API_KEY = "AIzaSyAHSiOywgrYgAP-p-0Tv8BRfVYyfLUSKVM";
    YouTubePlayerView youTubePlayerView;
    int REQUEST_VIDEO = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_xem_video);
        Intent intent = getIntent();
        linkvideo = intent.getStringExtra("Linkvideo");
        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.viewyoutube);
        youTubePlayerView.initialize(API_KEY, this);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.cueVideo(linkvideo);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(Xemvideo.this,REQUEST_VIDEO);
        }
        else{
            Toast.makeText(this, "Lỗi !", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_VIDEO) {
            youTubePlayerView.initialize(API_KEY, Xemvideo.this);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
