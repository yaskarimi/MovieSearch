package com.sematec.moviesearch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    Button btnOk;
    EditText edtName;
    List<String> list;
    RecyclerView recyclerView;
    String key;
     String name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOk=findViewById(R.id.btnOk);
        edtName = findViewById(R.id.edtName);
        recyclerView = findViewById(R.id.reycler_view);



        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final MovieListAdapter movieListAdapter = new MovieListAdapter(list);
                recyclerView.setAdapter(movieListAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this , LinearLayoutManager.VERTICAL , false));



                key=edtName.getText().toString();
                name = "http://www.omdbapi.com/?t=" + key;

                AsyncHttpClient asyncHttpClient = new AsyncHttpClient();


                asyncHttpClient.get(name , new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String result = null;
                        try {
                            result = jsonObject.getString("Title");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        movieListAdapter.name = result;

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                    }
                });


            }
        });



    }
}
