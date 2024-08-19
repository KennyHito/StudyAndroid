package com.example.studyandroid.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studyandroid.Adapter.ListAdapter;
import com.example.studyandroid.R;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        //首先找到对应的RecyclerView
        RecyclerView rv = findViewById(R.id.rv);

        //设置该属性可以提高性能,具体会有相应的文章分析
        rv.setHasFixedSize(true);

        //设置方向(横向还是纵向,还是其他方式)
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);

        //创建数据
        ArrayList<String> dataArr = new ArrayList<>();
        for (int i = 0; i < 100; i++){
            dataArr.add("数据"+i);
        }

        //设置适配器ListAdapter,并将数据传入进去
        ListAdapter adapter = new ListAdapter(this,dataArr);
        rv.setAdapter(adapter);
    }

}