package com.example.shohag.magicfinal;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

import java.util.ArrayList;
import java.util.List;


public class MenuActivity extends ActionBarActivity implements AbsListView.OnScrollListener {

    GridView grid;
    List<String> youtube_id;
    List<ThumbnailListener> thumbnailListenerList;
    CustomGrid adapter;
    AsyncTaskLoadThambernail asyncTaskLoadThambernail;
    boolean MORE_DATA_LOAD_ONCE_ASYNC = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setAppBarAndNavigationDrawer();
        thumbnailListenerList = new ArrayList<>();

        grid = (GridView) findViewById(R.id.grid);
        youtube_id = SampleData.getYoutubeThamNailData();

        asyncTaskLoadThambernail = new AsyncTaskLoadThambernail(MenuActivity.this, true, youtube_id);
        asyncTaskLoadThambernail.execute();


        adapter = new CustomGrid(MenuActivity.this, youtube_id, thumbnailListenerList);
        grid.setAdapter(adapter);


//        myAsyncTaskLoadFiles = new AsyncTaskLoadFiles(MenuActivity.this, false);
//        myAsyncTaskLoadFiles.execute();

        grid.setOnScrollListener(this);


        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //Toast.makeText(MenuActivity.this, "You Clicked at " + web[+position], Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(MenuActivity.this, PlayVideoActivity.class);
//                intent.putExtra("id", youtube_id[position]);
//                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
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

    private void setAppBarAndNavigationDrawer() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        new Drawer()
                .withActivity(this)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withHeader(R.layout.header)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.drawer_item_action_bar).withIcon(FontAwesome.Icon.faw_home).withIdentifier(1),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_multi_drawer).withIcon(FontAwesome.Icon.faw_magic).withIdentifier(2),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_non_translucent_status_drawer).withIcon(FontAwesome.Icon.faw_info).withIdentifier(3)

                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                        if (drawerItem instanceof Nameable) {
                            Toast.makeText(MenuActivity.this, MenuActivity.this.getString(((Nameable) drawerItem).getNameRes()), Toast.LENGTH_SHORT).show();
                        }

                        if (drawerItem.getIdentifier() == 1) {
//                            Intent intent = new Intent(SimpleDrawerActivity.this, ActionBarDrawerActivity.class);
//                            SimpleDrawerActivity.this.startActivity(intent);
                        } else if (drawerItem.getIdentifier() == 2) {
                            Intent intent = new Intent(MenuActivity.this, FavouriteActivity.class);
                            MenuActivity.this.startActivity(intent);
                        } else if (drawerItem.getIdentifier() == 3) {
//                            Intent intent = new Intent(SimpleDrawerActivity.this, SimpleNonTranslucentDrawerActivity.class);
//                            SimpleDrawerActivity.this.startActivity(intent);
                        }

                    }
                })
                .build();

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        Log.d("XXXXXXXXXXXXXXXX", "First Item " + firstVisibleItem + " Total Item " + totalItemCount);
        if (firstVisibleItem >= 10 && totalItemCount == 30) {
            adapter.updateData(SampleData.getYoutubeData());
//            if (MORE_DATA_LOAD_ONCE_ASYNC) {
//                Log.d("XXXXXXXXXXXXXXXX", "LOAD ONLY ONE TIME");
////                myAsyncTaskLoadFiles = new AsyncTaskLoadFiles(MenuActivity.this, true);
////                myAsyncTaskLoadFiles.execute();
//
//                MORE_DATA_LOAD_ONCE_ASYNC = false;
//            }
        }


    }


    public class AsyncTaskLoadThambernail extends AsyncTask<Void, Void, Void> {

        private Context context;
        private boolean FLAG_UPDATE = false;
        private List<String> youtube_id_2;


        public AsyncTaskLoadThambernail(Context context, boolean FLAG_UPDATE, List<String> youtube_id) {
            this.FLAG_UPDATE = FLAG_UPDATE;
            this.context = context;
            youtube_id_2 = youtube_id;


        }


        @Override
        protected Void doInBackground(Void... params) {
            for (int i = 0; i < youtube_id_2.size(); i++) {
                thumbnailListenerList.add(new ThumbnailListener(context, youtube_id_2.get(i)));
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

        }

    }
}
