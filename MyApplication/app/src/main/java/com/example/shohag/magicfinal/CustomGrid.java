package com.example.shohag.magicfinal;

/**
 * Created by Shohag on 21-Feb-15.
 */

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.List;


public class CustomGrid extends BaseAdapter {
    private Context mContext;
    //private String[] video_id;
    private List<String> videos;
    private List<ThumbnailListener> thumbnailListenerList;
    YouTubeThumbnailView youTubeThumbnailView;

    public CustomGrid(Context c, List<String> videos, List<ThumbnailListener> thumbnailListenerList) {
        Log.d("XXXXXXXXXXXXXXX", "Adapter Constructor "+videos.size());
        mContext = c;
        this.videos = videos;
        this.thumbnailListenerList=thumbnailListenerList;
        //this.video_id=video_id;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return videos.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        View vi = convertView;

        // TODO Auto-generated method stub
        // View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            //grid = new View(mContext);
            // grid
            convertView = inflater.inflate(R.layout.grid_single, null);
            youTubeThumbnailView = (YouTubeThumbnailView) convertView.findViewById(R.id.youtube_view);
            youTubeThumbnailView.initialize(DeveloperKey.DEVELOPER_KEY,thumbnailListenerList.get(position));
        } else {
            convertView = (View) convertView;
            convertView = inflater.inflate(R.layout.grid_single, null);
            youTubeThumbnailView = (YouTubeThumbnailView) convertView.findViewById(R.id.youtube_view);
            youTubeThumbnailView.initialize(DeveloperKey.DEVELOPER_KEY,thumbnailListenerList.get(position));
            return convertView;
        }
        return convertView;
    }

    public void updateData(List<String> dataItems) {

        for (int i = 0; i < dataItems.size(); i++)
            videos.add(dataItems.get(i));

        notifyDataSetChanged();
    }



}
