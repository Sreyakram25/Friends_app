package com.example.friendss_app;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4;
    AppCompatButton b1;
    String apiUrl="https://friendsapi-re5a.onrender.com/adddata" ;


@Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.activity_main);
    ed1 = (EditText) findViewById(R.id.yourname);
    ed2 = (EditText) findViewById(R.id.frnd);
    ed3 = (EditText) findViewById(R.id.nick);
    ed4 = (EditText) findViewById(R.id.describe);
    b1 = (AppCompatButton) findViewById(R.id.addbtn);

    b1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String name = ed1.getText().toString();
            String friendname = ed2.getText().toString();
            String nick = ed3.getText().toString();
            String descript = ed4.getText().toString();

            JSONObject friend = new JSONObject();
            try {
                friend.put("name", name);
                friend.put("friendName", friendname);
                friend.put("system_number", nick);
                friend.put("department", descript);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, apiUrl, friend  ,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(getApplicationContext(), "Added", Toast.LENGTH_LONG).show();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
            );

            // request queue
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(jsonObjectRequest);
        }
        });
    }
}









