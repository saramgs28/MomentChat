package com.saram.momentchat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.api.client.googleapis.auth.clientlogin.ClientLogin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/*
class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {

    private static MyApi myApiService = null;

    private Context context;

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        context = params[0].first;
        String name = params[0].second;

        try {
            //return myApiService.login(name).execute().getData();
            return myApiService.sayHi(name).execute().getData();
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
        //String username;
        //String password;
        EditText userName;
        EditText password;
        final String URL = "http://" + "http://localhost:8080/_ah/api/myApi/v1/login";
        switch(v.getId()) {
            case R.id.buttonlogin:
                //username = ((EditText)findViewById(R.id.id_username)).getText().toString();
                //password = ((EditText)findViewById(R.id.id_password)).getText().toString();
                userName = (EditText) findViewById(R.id.id_username);
                password = (EditText)findViewById(R.id.id_password);
                //new EndpointsAsyncTask().execute(new Pair<Context, String>(this, "Sarita"));
               // new EndpointsAsyncTask().execute(new Pair<Context, String>(this, username));
               // String dato = et1.getText().toString();
                //act = new Intent(this, MainActivity.class);
                //startActivity(act);
                if (userName.getText().toString().isEmpty() || password.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(), "Please enter the username and password", Toast.LENGTH_LONG).show();
                else
                {
                    try
                    {
                        JSONObject reqObject = new JSONObject();
                        String username = userName.getText().toString();
                        String pword = password.getText().toString();
                        reqObject.put("username", username);
                        reqObject.put("password", pword);
                        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST,URL, reqObject,
                                new Response.Listener<JSONObject>() {
                                    public void onResponse(JSONObject response) {
                                        Log.i(GlobalClass.TAG,"Received response: " + response.toString());
                                        loadingScreen.setVisibility(View.VISIBLE);
                                        loginPage.setVisibility(View.GONE);
                                        try {
                                            String tokenKey = response.getString("key");
                                            SharedPreferences.Editor editor = preferences.edit();
                                            editor.putString("key", tokenKey);
                                            editor.putString("source","native");
                                            editor.commit();
                                            Log.i(GlobalClass.TAG,"Added Preference");
                                        } catch (JSONException e) {
                                            Log.e(GlobalClass.TAG,"Failed to get token");
                                        }

                                        //Go to home page
                                        final String URL = "http://" + getString(R.string.host) + "/getinitial/";
                                        JsonArrayRequest req = new JsonArrayRequest(URL,
                                                new Response.Listener<JSONArray>() {
                                                    @Override
                                                    public void onResponse(JSONArray response) {
                                                        Log.i(GlobalClass.TAG,"Received Response");
                                                        Intent mainIntent = new Intent(getApplicationContext(),MainActivity.class);
                                                        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                        try {
                                                            JSONArray localities_array = response.getJSONObject(0).getJSONArray("localities");
                                                            JSONArray venues_array = response.getJSONObject(0).getJSONArray("venues");
                                                            JSONArray sports_array = response.getJSONObject(0).getJSONArray("sports");
                                                            mainIntent.putExtra("localities", localities_array.toString());
                                                            mainIntent.putExtra("venues", venues_array.toString());
                                                            mainIntent.putExtra("sports", sports_array.toString());
                                                        } catch (JSONException e) {
                                                            e.printStackTrace();
                                                        }
                                                        NativeLoginActivity.this.startActivity(mainIntent);
                                                        NativeLoginActivity.this.finish();

                                                    }
                                                }, new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                Log.i(GlobalClass.TAG, "Error: " + error.getMessage());
                                                Toast.makeText(getApplicationContext(),"Error connecting to the server",Toast.LENGTH_LONG).show();
                                            }
                                        });

                                        //Adding request to request queue
                                        GlobalClass.getInstance().addToRequestQueue(req);

                                    }
                                }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                String responseMsg = "";
                                try {
                                    if(error.networkResponse.data != null)
                                    {
                                        String body = new String(error.networkResponse.data,"utf-8");
                                        responseMsg = (new JSONObject(body)).getJSONArray("non_field_errors").getString(0);
                                        Log.i(GlobalClass.TAG, "Body: " + body);
                                    }
                                } catch(JSONException je)
                                {
                                    Log.e(GlobalClass.TAG,"Error: " + je.toString());
                                }catch (UnsupportedEncodingException e) {
                                    Log.e(GlobalClass.TAG, "Error: " + e.toString());
                                }
                                if(responseMsg.isEmpty())
                                    Toast.makeText(getApplicationContext(), "Error connecting to server", Toast.LENGTH_LONG).show();
                                else
                                    Toast.makeText(getApplicationContext(),responseMsg,Toast.LENGTH_LONG).show();
                            }

                        });
                        GlobalClass.getInstance().addToRequestQueue(req);
                    } catch(JSONException je)
                    {
                        Log.i(GlobalClass.TAG,"Couldn't send username and password");
                    }
                }
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
