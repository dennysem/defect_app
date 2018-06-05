package com.example.denny_sem.defect;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class SaveActivity extends AppCompatActivity {
    private EditText city  = null;
    private EditText street  = null;
    private EditText number  = null;
    private EditText sender  = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        city = findViewById(R.id.cityEditText);
        street = findViewById(R.id.streetEditText);
        number = findViewById(R.id.numberEditText);
        sender = findViewById(R.id.senderEditText);
    }

    public void saveToDB(View view) {
        JSONObject defect = new JSONObject();
        try {
            defect.put("city", city.getText().toString());
            defect.put("street", street.getText().toString());
            defect.put("number", number.getText().toString());
            defect.put("sender", sender.getText().toString());
        } catch (Exception e) {
            finish();
        }
        String cityText = city.getText().toString();
        String streetText = street.getText().toString();
        String numberText = number.getText().toString();
        String senderText = sender.getText().toString();
        String url = "http://35.163.88.31:80/write_defect?city=" + cityText + "&street="
                + streetText + "&number=" + numberText + "&sender=" + senderText;
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<>();
                params.put("city", city.getText().toString());
                params.put("street", street.getText().toString());
                params.put("number", number.getText().toString());
                params.put("sender", sender.getText().toString());
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };
        Volley.newRequestQueue(this).add(postRequest);
        finish();
    }
}
