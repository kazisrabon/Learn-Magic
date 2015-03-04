package com.example.shohag.magicfinal;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;


public class PlayVideoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    YouTubePlayerView youTubePlayerView;
    private YouTubePlayer youTubePlayerThis;
    Context context;
    public String videoId;


//    public PlayVideoActivity(String videoId) {
//        this.videoId = videoId;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //getActionBar().setTitle("Youtube Play");
        //setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        videoId=getIntent().getStringExtra("id");

        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player_view);
        youTubePlayerView.initialize(DeveloperKey.DEVELOPER_KEY, this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_play_video, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayerThis = youTubePlayer;
        youTubePlayerThis.setPlaybackEventListener(new MHPlaybackEventListener());
        youTubePlayerThis.setPlayerStateChangeListener(new MHPlayerStateChangeListener());
        youTubePlayerThis.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
        youTubePlayerThis.cueVideo(videoId);

    }


    private class MHPlaybackEventListener implements YouTubePlayer.PlaybackEventListener {

        @Override
        public void onBuffering(boolean arg0) {
            Toast.makeText(PlayVideoActivity.this, "onBuffering video...", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPaused() {
            Toast.makeText(PlayVideoActivity.this, "onPaused video...", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPlaying() {
            Toast.makeText(PlayVideoActivity.this, "onPlaying video...", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onSeekTo(int arg0) {
            Toast.makeText(PlayVideoActivity.this, "onSeekTo video...", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStopped() {
            Toast.makeText(PlayVideoActivity.this, "onStopped video...", Toast.LENGTH_SHORT).show();
        }
    }

    private class MHPlayerStateChangeListener implements YouTubePlayer.PlayerStateChangeListener {

        @Override
        public void onAdStarted() {
            Toast.makeText(PlayVideoActivity.this, "onAdStarted video...", Toast.LENGTH_SHORT).show();
        }


        @Override
        public void onLoaded(String arg0) {
            youTubePlayerThis.play();
            // pauseButton.setVisibility(View.VISIBLE);

        }

        @Override
        public void onLoading() {
            Toast.makeText(PlayVideoActivity.this, "onLoading video...", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onVideoEnded() {
            Toast.makeText(PlayVideoActivity.this, "onVideoEnded video...", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {
            Toast.makeText(PlayVideoActivity.this, "onError video...", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onVideoStarted() {
            Toast.makeText(PlayVideoActivity.this, "onVideoStarted video...", Toast.LENGTH_SHORT).show();
        }

    }
}
