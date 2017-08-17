package com.example.trest.weatherapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String key = "0cf69727ca54c41e87e68f6c9caf9f84";


        Button btn = (Button) findViewById(R.id.btn1);
        final EditText editText = (EditText) findViewById(R.id.cityinp);
        final TextView textView = (TextView) findViewById(R.id.result);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String city = editText.getText().toString();

                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);


                String url="https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid="+key;


                JsonObjectRequest request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("JSON",response.toString());

                        try {
                            JSONArray jsonArray = response.getJSONArray("weather");
                            String disc = jsonArray.getJSONObject(0).getString("description");
                            textView.setText(disc);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });


                queue.add(request);



            }
        });

    }
}
