package com.example.ishita.notesapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

public class NotesActivity extends Activity {
    private ArrayList<Pair> myDataArray = new ArrayList<Pair>();
    private String optionSelected;
    private String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);


        /* WORKS TO TRANSFER MYDATASET */
        /* Needs to be changed to retreieve OPTION and FILE */
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                myDataArray = null;
            } else {
                //optionSelected = extras.getString("OPTION");
                myDataArray = (ArrayList<Pair>) extras.getSerializable("DATA");
            }
        } else {
            myDataArray = (ArrayList<Pair>)getIntent().getSerializableExtra("DATA");
        }

    }
}