package com.saram.momentchat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FrontActivity extends AppCompatActivity implements OnClickListener {

    private Button button_login;
    private Button button_image;
    private Button button_video;
    private Button button_audio;
    Intent act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);

        button_login = (Button) findViewById(R.id.buttonlogin);
        button_image = (Button) findViewById(R.id.buttonimage);
        button_video = (Button) findViewById(R.id.button_video);
        button_audio = (Button) findViewById(R.id.button_audio);

        button_audio.setOnClickListener(this);
        button_video.setOnClickListener(this);
        button_login.setOnClickListener(this);
        button_image.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == button_image){
            act=new Intent(this,MainActivity.class);
        }
        if(v == button_login){
            //act=new Intent(this,Login.class);
            //act=new Intent(this,Login.class);
        }
        if(v == button_video){
            act = new Intent(this, MainVideo.class);
        }
        if(v == button_audio){
            act = new Intent(this, MainAudio.class);
        }
        startActivity(act);
    }
}
