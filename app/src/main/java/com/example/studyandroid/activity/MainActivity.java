package com.example.studyandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studyandroid.R;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "MainActivity";
    private  OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        // 获取xml中第一个按钮
        Button button1 = findViewById(R.id.btnClick1);
        button1.setOnClickListener(this);
        //等同于 findViewById(R.id.btnClick1).setOnClickListener(this);
        String buttonTitle = button1.getText().toString();
        button1.setText(buttonTitle+"(新)");

        // 获取xml中第二个按钮
        Button button2 = findViewById(R.id.btnClick2);
        button2.setOnClickListener(this);

        // 获取xml中第三个按钮
        Button button3 = findViewById(R.id.btnClick3);
        button3.setOnClickListener(this);

        // 获取xml中第四个按钮
        Button button4 = findViewById(R.id.btnClick4);
        button4.setOnClickListener(this);

        Button requestNetworkBtn = findViewById(R.id.requestNetworkBtn);
        requestNetworkBtn.setOnClickListener(this);

        //学习,这就是方法调用的使用
        studyAndroid();
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnClick1:
                //输入框
                EditText usernameText = findViewById(R.id.username);
                String name = usernameText.getText().toString().trim();
                if (TextUtils.isEmpty(name)){
                    Toast.makeText(MainActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }
                showMethod(name);
                break;
            case R.id.btnClick2:
                //Toast样式
                showMethod("哈哈哈哈");
                break;
            case R.id.btnClick3:
                //跳转普通页面
                skipNextPage(0);
                break;
            case R.id.btnClick4:
                //跳转列表页面
                skipNextPage(1);
                break;
            case R.id.requestNetworkBtn:
                //网络请求
                requestNetwork();
                break;
            default:
                break;
        }
    }

    /************************************以下为自己创建的方法***********************************/
    public void showMethod(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    public void skipNextPage(int type) {
        if (type == 0) {
            // 跳转到下一个页面
            Intent intent = new Intent(MainActivity.this, NextActivity.class);
            // 启动
            startActivity(intent);
        }else if (type == 1){
            Intent intent = new Intent(MainActivity.this, ListActivity.class);
            startActivity(intent);
        }
    }

    public void studyAndroid(){
        int a = 10;
        Log.d(TAG, "这是一条数据打印,studyAndroid: "+a);
    }

    public void requestNetwork(){
        new Thread(){
            @Override
            public void run() {
                super.run();
                //免费的api: https://blog.csdn.net/qq_41366114/article/details/122001547
                Request request = new Request.Builder().url("https://www.yuanxiapi.cn/api/pingspeed/?host=baidu.com").build();
                try {
                    Response response =  client.newCall(request).execute();
                    if (response.isSuccessful()){
                        String string = response.body().string();
                        Log.d(TAG, "request network data: "+string);
                    }else{
                        Log.d(TAG, "request network data error");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }
}
