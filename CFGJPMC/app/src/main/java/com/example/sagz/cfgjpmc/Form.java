package com.example.sagz.cfgjpmc;

import android.content.SharedPreferences;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Form extends ActionBarActivity
{
    TextView tv,curr_date;
    EditText delay;
    Button submit;
    int task_value;
    String url;
    String username,schoolname;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(Form.this);
        username=pref.getString("username","");
        schoolname=pref.getString("schoolname","");

        tv=(TextView)findViewById(R.id.textView);
        curr_date=(TextView)findViewById(R.id.textView2);

        DateFormat dateFormat = new SimpleDateFormat("MM/dd");
        Date date = new Date();
        curr_date.setText(dateFormat.format(date));
    }

    public void onclick(View v)
    {
        switch (v.getId())
        {
            case R.id.button4:

                url="http://team15.esy.es/app_details.php";

                ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
                postParameters.add(new BasicNameValuePair("task",""+task_value));
                postParameters.add(new BasicNameValuePair("date",curr_date.getText().toString()));
                postParameters.add(new BasicNameValuePair("username",username));
                postParameters.add(new BasicNameValuePair("schoolname",schoolname));

                Toast.makeText(this,username+schoolname,Toast.LENGTH_LONG).show();
                try
                {
                    String response = CustomHttpClient.executeHttpPost(
                            url,  // in case of a remote server
                            postParameters);

                    Toast.makeText(Form.this,"Submitted...",Toast.LENGTH_LONG).show();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

                break;
        }
    }


    public void radioclick(View v)
    {
        switch (v.getId())
        {
                case R.id.radioButton:
                task_value=1;
                break;

            case R.id.radioButton2:
                task_value=2;
                break;

            case R.id.radioButton3:
                task_value=3;
                break;

            case R.id.radioButton4:
                task_value=4;
                break;

            case R.id.radioButton5:
                task_value=5;
                break;

            case R.id.radioButton6:
                task_value=6;
                break;

            case R.id.radioButton7:
                task_value=7;
                break;

            case R.id.radioButton8:
                task_value=8;
                break;

            case R.id.radioButton9:
                task_value=9;
                break;

            case R.id.radioButton10:
                task_value=10;
                break;
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_form, menu);
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
