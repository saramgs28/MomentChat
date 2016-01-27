package com.saram.momentchat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FrontActivity extends AppCompatActivity implements OnClickListener {

    private Button button_login;
    private Button button_signin;
    Intent act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);

        button_login = (Button) findViewById(R.id.buttonlogin);
        button_signin = (Button) findViewById(R.id.buttonsignin);

        button_login.setOnClickListener(this);
        button_signin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == button_signin){
            act=new Intent(this,signin.class);
        }
        if(v == button_login){
            act=new Intent(this,MainActivity.class);
            //act=new Intent(this,Login.class);
        }
        startActivity(act);
    }
}
