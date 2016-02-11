package com.saram.momentchat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
/*
class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    //private static MyApi myApiService = null;
    private static MyApi myApiService = null;
    private Context context;

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if(myApiService == null) {  // Only do this once
            /*MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://momentchat-1201.appspot.com/_ah/api/");*/
       /*     MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
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
*/
public class Login extends AppCompatActivity implements OnClickListener {

    private EditText editTextUsername;
    private EditText editTextPassword;

    private Button button_login;
    private Button button_cancel;
    private Button button_audio;
    private Button button_video;
    Intent act = new Intent(this, MainActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    @Override
    public void onClick(View v) {

        if(v == button_login){
            //LoginUser();
            act = new Intent(this, MainActivity.class);
        }
        if(v == button_cancel){
            act = new Intent(this, FrontActivity.class);
        }
        if(v == button_audio){
         //   act = new Intent(this, MainAudio.class);
        }
        if(v == button_video){
          //  act = new Intent(this, MainVideo.class);
        }
        startActivity(act);
    }

    private void init(){
        button_login = (Button) findViewById(R.id.buttonlogin);
        button_login.setOnClickListener(this);

        button_cancel = (Button) findViewById(R.id.button_cancel);
        button_cancel.setOnClickListener(this);

        button_audio = (Button) findViewById(R.id.button_audio);
        button_audio.setOnClickListener(this);

        button_video = (Button) findViewById(R.id.button_video);
        button_video.setOnClickListener(this);

        editTextUsername = (EditText) findViewById(R.id.id_username);
        editTextPassword = (EditText) findViewById(R.id.id_password);
    }

    /*private void LoginUser(){
        final String username = editTextUsername.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        new EndpointsAsyncTask().execute(new Pair<Context, String>(this, username));

        //display in long period of time
        Toast.makeText(getApplicationContext(), "HI  "+username+" WHAT'S UP?",
                Toast.LENGTH_LONG).show();

        act = new Intent(this, MainActivity.class);
    }*/
}
