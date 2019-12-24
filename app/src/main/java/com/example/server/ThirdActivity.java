package com.example.server;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ThirdActivity extends AppCompatActivity {

    private final String URL = "https://api.myjson.com/bins/fz8ir";
    private final String URL_string = "https://www.decodexlab.com/download/string.html";
    private RequestQueue queue;
    private Button get3, getHTML3, next3;
    private TextView textResult3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_third );
        get3 = findViewById( R.id.get3 );
        getHTML3 = findViewById( R.id.get3a);
        textResult3 = findViewById( R.id.text_view_result3 );
        next3 = findViewById(R.id.next3);
        queue = Volley.newRequestQueue( this );

        get3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                get();
            }
        });


         getHTML3.setOnClickListener(new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            getStringObject( URL_string );
        }
    });
        next3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next2 = new Intent( ThirdActivity.this, FourthActivity.class );
                startActivity( next2 );
            }
        });
}
        private void get(){
        JsonArrayRequest arrayRequest = new JsonArrayRequest( Request.Method.GET, URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d( "Response: ", response.toString() );
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject( i );
                                Log.d( "Items: ", object.getString( "name" ) + " / " + object.getString( "categorie" ) );
                                textResult3.append( object.getString( "name" ) + " / " + object.getString( "categorie" ) );
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d( "Error: ", error.getMessage() );
                    }
                } );
        queue.add( arrayRequest );
    }

    public void getStringObject(String url) {
        StringRequest stringRequest = new StringRequest( Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d( "String: ", response.toString() );
                        textResult3.append( response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d( "String error: ", error.getMessage() );
                    }
                } );
        queue.add( stringRequest );
        }
    }