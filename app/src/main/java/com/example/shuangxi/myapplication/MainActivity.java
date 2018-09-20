package com.example.shuangxi.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public Button bt1;
    public Button bt2;

    public void init() {

        bt1 = (Button)findViewById(R.id.button1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(MainActivity.this, EditName.class);
                startActivityForResult(toy,2);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        bt2 = (Button) findViewById(R.id.button2);

        final int _requestCode = requestCode;
        final int _resultCode = resultCode;
        final Intent _data = data;

        bt2.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {

                                       if (_requestCode == 2) {

                                           if(_resultCode == Activity.RESULT_OK){

                                               String name = _data.getStringExtra("name");


////                                               String title = getResources().getString(R.string.chooser_title);
//                                               String title = getResources().getString(R.string.app_name);
//                                               Intent chooser = Intent.createChooser(sendIntent, title);
//                                               if (sendIntent.resolveActivity(getPackageManager()) != null) {
//                                                   startActivity(chooser);}


                                               Intent sendIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(name) );
//                                               sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my contact to show.");
//                                               sendIntent.setType("text/plain");
                                               String title = "Pick an app to show the name ";
                                               Intent chooser = Intent.createChooser(sendIntent, title);
                                               if (sendIntent.resolveActivity(getPackageManager()) != null) {
                                                   startActivity(chooser);}


                                           }
                                           if (_resultCode == Activity.RESULT_CANCELED) {
                                               String name = _data.getStringExtra("name");

                                               Context context = getApplicationContext();
                                               CharSequence text = "You enter an incorrect name: " + name;
                                               int duration = Toast.LENGTH_SHORT;

                                               Toast toast = Toast.makeText(context, text, duration);
                                               toast.show();
                                           }
                                       }

                                   }
                               }
        );


    }//onActivityResult


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
}
