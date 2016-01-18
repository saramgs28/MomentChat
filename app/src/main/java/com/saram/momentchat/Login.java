package com.saram.momentchat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class Login extends AppCompatActivity implements OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      //  setSupportActionBar(toolbar);
        //Enable buttons
        Button button_login = (Button) findViewById(R.id.buttonlogin);
        button_login.setOnClickListener(this);
        Button button_signin = (Button) findViewById(R.id.buttonsignin);
        button_signin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
      Intent act;
        switch(v.getId()) {
            case R.id.buttonlogin:
                act = new Intent(this, MainActivity.class);
                startActivity(act);
                break;

            case R.id.buttonsignin:
                act = new Intent(this, signin.class);
                startActivity(act);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
