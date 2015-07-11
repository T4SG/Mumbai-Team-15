package com.example.sagz.cfgjpmc;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferListener;


public class MainActivity extends ActionBarActivity
{

    Button btn,upload;
    String motherpath= Environment.getExternalStorageDirectory()+"/CFG/";

    String mypath = Environment.getExternalStorageDirectory() + "/CFG/";

    protected boolean taken;

    protected static final String PHOTO_TAKEN	= "photo_taken";

    static final String FTP_HOST= "31.170.165.28";

    /*********  FTP USERNAME ***********/
    static final String FTP_USER = "u812042114";

    /*********  FTP PASSWORD ***********/
    static final String FTP_PASS  ="tequGu";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i=new Intent(this,Login.class);
        startActivity(i);


        btn=(Button)findViewById(R.id.button);
        upload=(Button)findViewById(R.id.button2);

        File f=new File(motherpath);

        if(!f.exists())
        {
            f.mkdirs();
        }


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                        Locale.getDefault()).format(new Date());


                File file = new File(mypath,""+timestamp+".jpg");

                Uri outputFileUri = Uri.fromFile(file);

                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE );

                intent.putExtra( MediaStore.EXTRA_OUTPUT, outputFileUri );


                startActivityForResult(intent,0);

            }
        });


        upload.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String paths[];

                File ff=new File(motherpath+"20150711_141152.jpg");

                uploadFile(ff);
              /*  paths=ff.list();

                for(String image_path:paths)
                {
                    File fff=new File(motherpath+image_path);
                    Log.i(image_path,"dsad");
                    uploadFile(fff);
                    Toast.makeText(MainActivity.this,image_path,Toast.LENGTH_SHORT).show();
                }
*/

            }
        });


    }




    public void uploadFile(File fileName){


        FTPClient client = new FTPClient();

        try {

            client.connect(FTP_HOST,21);
            client.login(FTP_USER, FTP_PASS);
            client.setType(FTPClient.TYPE_BINARY);
           // client.changeDirectory("/upload/");

            client.upload(fileName, new MyTransferListener());

        } catch (Exception e) {
            e.printStackTrace();
            try
            {
                client.disconnect(true);
            }
            catch (Exception e2) {
                e2.printStackTrace();
                Log.i("dsad","dasd");
            }
        }

    }

    /*******  Used to file upload and show progress  **********/

    public class MyTransferListener implements FTPDataTransferListener {

        public void started() {

            btn.setVisibility(View.GONE);
            // Transfer started
            Toast.makeText(getBaseContext(), " Upload Started ...", Toast.LENGTH_SHORT).show();
            //System.out.println(" Upload Started ...");
        }

        public void transferred(int length) {

            // Yet other length bytes has been transferred since the last time this
            // method was called
            Toast.makeText(getBaseContext(), " transferred ..." + length, Toast.LENGTH_SHORT).show();
            //System.out.println(" transferred ..." + length);
        }

        public void completed() {

            btn.setVisibility(View.VISIBLE);
            // Transfer completed

            Toast.makeText(getBaseContext(), " completed ...", Toast.LENGTH_SHORT).show();
            //System.out.println(" completed ..." );
        }

        public void aborted() {

            btn.setVisibility(View.VISIBLE);
            // Transfer aborted
            Toast.makeText(getBaseContext()," transfer aborted", Toast.LENGTH_SHORT).show();
            //System.out.println(" aborted ..." );
        }

        public void failed() {

            btn.setVisibility(View.VISIBLE);
            // Transfer failed
            System.out.println(" failed ..." );
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode)
        {
            case 0:
                Toast.makeText(MainActivity.this, "Cancelled ! ", Toast.LENGTH_SHORT).show();
                break;

            case -1:

                onPhotoTaken();
                break;
        }


    }

    protected void onPhotoTaken()
    {


        taken = true;

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;

        Bitmap bitmap = BitmapFactory.decodeFile( mypath, options );

    }

    @Override
    protected void onRestoreInstanceState( Bundle savedInstanceState){

        if( savedInstanceState.getBoolean(MainActivity.PHOTO_TAKEN ) ) {
            onPhotoTaken();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
