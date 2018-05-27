package com.roshan.dic.en_app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Activity_Contact extends AppCompatActivity {

    private EditText edName,edEmail,edMessage;
    private RequestQueue requestQueue;
    String url = "http://localhost/dashboard/Android-web/Contact.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__contact);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        edName = (EditText) findViewById(R.id.edname);
        edEmail = (EditText) findViewById(R.id.edemail);
        edMessage = (EditText) findViewById(R.id.edmessage);

        requestQueue = Volley.newRequestQueue(this);
    }

    public void btnsendclick(View view)
    {
        final String name,email,message;
        name = edName.getText().toString();
        email = edEmail.getText().toString();
        message = edMessage.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,url , new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                Toast.makeText(Activity_Contact.this, "Successfull", Toast.LENGTH_SHORT).show();
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Toast.makeText(Activity_Contact.this, "Failed "+error, Toast.LENGTH_LONG).show();
            }

        })
        {
          protected Map<String,String> getParams()
          {
              Map<String,String> data = new HashMap<>();
              data.put("Name",name);
              data.put("Email",email);
              data.put("Message",message);
              return data;
          }
        };
        requestQueue.add(stringRequest);

    }

}
