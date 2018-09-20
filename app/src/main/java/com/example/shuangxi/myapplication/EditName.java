package com.example.shuangxi.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;


public class EditName extends AppCompatActivity {

    public EditText addName;

    public boolean isNameLegal(String name) {

        String[] first_last_name = name.trim().split("\\s+");
        // assume that the name only allows first name and last name, first name and last name must
        //contain a sequence of alphabetical characters

        if (first_last_name.length == 2 && first_last_name[0].matches("[a-zA-Z]+") &&
        first_last_name[1].matches("[a-zA-Z]+")) return true;

        else return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_name);


        addName = (EditText) findViewById(R.id.editText);
        addName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_DONE) {

                    String text = addName.getText().toString();
                    Intent myIntent = new Intent(EditName.this, MainActivity.class);
                    myIntent.putExtra("name",text);

                    // if the name is legal
                    if (isNameLegal(text)) {
                        setResult(Activity.RESULT_OK, myIntent);
                    } else {
                        setResult(Activity.RESULT_CANCELED, myIntent);
                    }

                 // in any cases, terminate the activity
                finish();
                }
                return false;
            }
        });
    }
}
