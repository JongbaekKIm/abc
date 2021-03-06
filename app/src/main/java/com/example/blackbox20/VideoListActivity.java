package com.example.blackbox20;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class VideoListActivity extends AppCompatActivity {

    ListView listView;
    List<String> filelist = new ArrayList<>();
    VideoView mVideoView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);
        listView = (android.widget.ListView) findViewById(R.id.ListView);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, filelist));
        listRaw(filelist);

        mVideoView = (VideoView) findViewById(R.id.videoView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String st=(String)filelist.get(position);
                Uri uri = Uri.parse("android.resource://" + getPackageName() + "/raw/"+st);
                Intent intent = new Intent(getApplicationContext(), VideoViewActivity.class);
                intent.putExtra("uri",uri.toString());
                startActivity(intent);
            }
        });
    }

    public void listRaw(List<String> fileList) {
        Field[] fields = R.raw.class.getFields();
        for (int i = 0; i < fields.length; i++) {
            fileList.add(fields[i].getName());
        }
    }



}
