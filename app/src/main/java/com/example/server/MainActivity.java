package com.example.server;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

import javax.sql.StatementEvent;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TextView textResult;
    private Button get, next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        textResult = findViewById( R.id.text_view_result );
        get = findViewById(R.id.get);
        next = findViewById(R.id.next);

        get.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFunction();
            }
        });
        next.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent( MainActivity.this, SecondActivity.class);
                startActivity(next);
            }
        } );
    }
    private void getFunction(){
        OkHttpClient client = new OkHttpClient();
        //String url = "https://api.myjson.com/bins/kp9wz";
        String url = "https://api.openweathermap.org/data/2.5/weather?q=Catania,Italy&appid=5360a3c3ac8bf01b8d0c14ef27072b7c";

                Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();

                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textResult.setText(myResponse);
                        }
                    });

                }
            }
        });

    }
}

