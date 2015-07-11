package com.example.sagz.cfgjpmc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Login extends ActionBarActivity
{

    EditText username;
    EditText password;
    String url;
    String user_name;
    String schoolname;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        username=(EditText)findViewById(R.id.editText2);
        password=(EditText)findViewById(R.id.editText3);

    }

    public void click(View v)
    {
        switch (v.getId())
        {
            case R.id.button5:

            url="http://team15.esy.es/app_login.php";

                ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
                postParameters.add(new BasicNameValuePair("username",""+username.getText().toString()));
                postParameters.add(new BasicNameValuePair("password",password.getText().toString()));

                try
                {
                    String response = CustomHttpClient.executeHttpPost( url,postParameters);

                    String result = response.toString();
                    Log.i("dsa",result);
                    try{
                        //returnString = "";
                        JSONArray jArray = new JSONArray(result);
                        for(int i=0;i<jArray.length();i++)
                        {

                            JSONObject json_data = jArray.getJSONObject(i);

                            //Get an output to the screen
                            user_name = json_data.getString("username");

                           schoolname = json_data.getString("schoolname");


                        }



                        SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(Login.this);
                        SharedPreferences.Editor editor=pref.edit();
                        editor.putString("username",user_name);
                        editor.putString("schoolname",schoolname);
                        editor.apply();

                        Intent ii=new Intent(Login.this,Form.class);
                        startActivity(ii);
                    }
                    catch(JSONException e){
                        Log.e("log_tag", "Error parsing data " + e.toString());
                    }


                }
                catch (Exception e)
                {
                    Log.e("log_tag", "Error in http connection!!" + e.toString());
                }

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
