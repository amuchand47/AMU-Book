package com.herokuapp.amulibraray.amulibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.ReceiverCallNotAllowedException;
import android.os.Bundle;
import android.os.RecoverySystem;
import android.view.View;
import android.widget.ImageView;

import com.herokuapp.amulibraray.amulibrary.Adapter.HomeAdapter;
import com.herokuapp.amulibraray.amulibrary.Model.HomeScreen;
import com.herokuapp.amulibraray.amulibrary.Notice.NoticeActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ImageView notes,notice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notes = (ImageView)findViewById(R.id.notes);
        notice = (ImageView)findViewById(R.id.notice);

        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             startActivity(new Intent(MainActivity.this, NoticeActivity.class));
            }
        });

        notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NoticeActivity.class));
            }
        });
    }
}