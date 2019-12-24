package com.example.server;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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


public class FourthActivity extends AppCompatActivity {

    private final String URLTEACHERS = "http://192.168.43.156:3000/teachers";
    private final String URLSTUDENTS = "http://192.168.43.156:3000/students";
    //private final String URL_string = "https://www.decodexlab.com/download/string.html";
    private RequestQueue queue;
    private Button get3, getHTML3;
    private TextView textResult3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_fourth );
        get3 = findViewById( R.id.get3 );
        getHTML3 = findViewById( R.id.get3a);
        textResult3 = findViewById( R.id.text_view_result3 );
        queue = Volley.newRequestQueue( this );

        get3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                getteachers();
            }
        });


        getHTML3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                getstudents();
            }
        });/*
        getHTML3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                getStringObject( URL_string );
            }
        });
        */
    }
    private void getteachers(){
        JsonArrayRequest arrayRequest = new JsonArrayRequest( Request.Method.GET, URLTEACHERS,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d( "Response: ", response.toString() );
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject( i );
                                //Log.d( "Items: ", object.getString( "name" ) + " / " + object.getString( "hours" ) );
                                textResult3.append( object.getString( "fiscalCode") + "\n" + object.getString( "name" ) + " "
                                        + object.getString( "surname" ) + "\n" + object.getString( "dateOfBirth") + "\n\n");
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
                        error.printStackTrace();
                        Toast.makeText(FourthActivity.this, "Errore di rete", Toast.LENGTH_SHORT).show();
                    }
                } );
        queue.add( arrayRequest );
    }

    private void getstudents(){
        JsonArrayRequest arrayRequest = new JsonArrayRequest( Request.Method.GET, URLSTUDENTS,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d( "Response: ", response.toString() );
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject( i );
                                //Log.d( "Items: ", object.getString( "name" ) + " / " + object.getString( "hours" ) );
                                textResult3.append( object.getString( "fiscalCode") + "\n" + object.getString( "name" ) + " "
                                        + object.getString( "surname" ) + "\n" + object.getString( "dateOfBirth") + "\n\n");
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
                        error.printStackTrace();
                        Toast.makeText(FourthActivity.this, "Errore di rete", Toast.LENGTH_SHORT).show();
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
                        error.printStackTrace();
                        Toast.makeText(FourthActivity.this, "Errore di rete", Toast.LENGTH_SHORT).show();
                    }
                } );
        queue.add( stringRequest );
    }
}