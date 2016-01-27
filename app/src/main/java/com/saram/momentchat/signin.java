package com.saram.momentchat;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.saram.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

class EndpointsAsyncTask2 extends AsyncTask<Pair<Context, String>, Void, String> {
    //private static MyApi myApiService = null;
    private static MyApi myApiService = null;
    private Context context;

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if(myApiService == null) {  // Only do this once
            /*MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://momentchat-1201.appspot.com/_ah/api/");*/
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://momentchat-1201.appspot.com/_ah/api/");

            myApiService = builder.build();
        }

        context = params[0].first;
        String name = params[0].second;

        try {
            return myApiService.login(name).execute().getData();
            //return myApiService.sayHi(name).execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }
}

public class signin extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextEmail;

    private Button button_accept;
    private Button button_cancel;
    Intent act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        init();
    }

    @Override
    public void onClick(View v) {

        if(v == button_accept){
            registerUser();
        }
        if(v == button_cancel){
            act = new Intent(this, FrontActivity.class);
        }
        startActivity(act);
    }

    private void init(){
        button_accept = (Button) findViewById(R.id.button_accept);
        button_accept.setOnClickListener(this);

        button_cancel = (Button) findViewById(R.id.button_cancel);
        button_cancel.setOnClickListener(this);

        editTextUsername = (EditText) findViewById(R.id.id_username);
        editTextPassword = (EditText) findViewById(R.id.id_password);
        editTextEmail = (EditText) findViewById(R.id.id_email);
    }

    private void registerUser(){
        final String username = editTextUsername.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String email = editTextPassword.getText().toString().trim();

        //PODRE PONER EndpointsAsyncTask Y ENVIAR LOS DATOS ETC??
        new EndpointsAsyncTask2().execute(new Pair<Context, String>(this, username));

        //SI TODO SALE BIEN MANDAME UNA TOAST

        //display in long period of time
        Toast.makeText(getApplicationContext(), "WELCOME  "+username+" TO MOMENTCHAT",
                Toast.LENGTH_LONG).show();

        act = new Intent(this, MainActivity.class);
    }
}
