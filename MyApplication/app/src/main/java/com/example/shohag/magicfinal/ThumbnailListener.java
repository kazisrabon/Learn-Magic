package com.example.shohag.magicfinal;

import android.content.Context;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

/**
 * Created by Shohag on 21-Feb-15.
 */
public class ThumbnailListener implements YouTubeThumbnailView.OnInitializedListener, YouTubeThumbnailLoader.OnThumbnailLoadedListener {

    String videoID;
    Context context;

    public ThumbnailListener(Context context, String vidId) {
        videoID = vidId;
        this.context = context;
    }

    @Override
    public void onInitializationFailure(YouTubeThumbnailView thumbView,
                                        YouTubeInitializationResult initResult) {
        thumbView.setImageResource(R.drawable.no_thumbnail);

    }

    @Override
    public void onInitializationSuccess(YouTubeThumbnailView thumbView,
                                        YouTubeThumbnailLoader thumbLoader) {
        thumbLoader.setOnThumbnailLoadedListener(this);

        thumbView.setImageResource(R.drawable.loading_thumbnail);

        thumbLoader.setVideo(videoID);

    }

    @Override
    public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {

    }

    @Override
    public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {
        youTubeThumbnailView.setImageResource(R.drawable.no_thumbnail);

    }


}
