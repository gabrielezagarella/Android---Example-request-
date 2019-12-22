package com.example.server;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SecondActivity extends AppCompatActivity {

    private TextView textResult2;
    private RequestQueue queque;
    private Button get2, next2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_second );

        textResult2 = findViewById( R.id.text_view_result2 );
        get2 = findViewById( R.id.get2 );
        next2 = findViewById(R.id.next2);
        queque = Volley.newRequestQueue( this );

        get2.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                jsonParse();
            }
        } );

        next2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next2 = new Intent( SecondActivity.this, ThirdActivity.class );
                startActivity( next2 );
            }
        });
    }

    private void jsonParse(){
        String url = "https://api.myjson.com/bins/kp9wz";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, (String) null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("employees");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject user = jsonArray.getJSONObject(i);
                                String firstName = user.getString("firstname");
                                String age = user.getString("age");
                                String mail = user.getString("mail");
                                textResult2.append(firstName + ", " + String.valueOf(age) + ", " + mail + "\n\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(SecondActivity.this, "Network failure", Toast.LENGTH_SHORT).show();
            }
        });
        queque.add(request);
    }
}

/*
    private void jsonParse(){
        String url = "http://localhost:4005/films";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("film");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject user = jsonArray.getJSONObject(i);
                                String firstName = user.getString("_id");
                                String age = user.getString("title");
                                String mail = user.getString("gender");
                                String exitDate = user.getString("exitDate");
                                String duration = user.getString("duration");
                                String cast = user.getString("cast");
                                String direction = user.getString("direction");
                                String startingTime = user.getString("startingTime");
                                textView.append(firstName + ", " + String.valueOf(age) + ", " + mail  + ", "+ exitDate  + ", " +
                                        String.valueOf(duration)  + ", " + cast  + ", " + direction  + ", " + String.valueOf(startingTime) + "\n\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(MainActivity.this, "Errore di rete", Toast.LENGTH_SHORT).show();
            }
        });
        queque.add(request);
    }
}

 */

