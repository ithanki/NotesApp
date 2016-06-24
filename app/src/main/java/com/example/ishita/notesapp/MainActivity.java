package com.example.ishita.notesapp;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private HashMap<String, String> notesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notesList = new HashMap<String, String>();
        //call some function to read directory to fill notesList
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id) {
            case R.id.menu_new:
                //perform action to call activity_notes
                return true;
            case R.id.menu_delete:
                //function to delete marked note(s)
                break;
            case R.id.menu_edit:
                //performs action to call activity_notes w/flag for edit
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showSimplePopUp() {
        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("Confirmation");
        helpBuilder.setMessage("Delete selected note?");
        helpBuilder.setPositiveButton("Ok",
           new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int which) {
                  //popup to confirm delete.
               }
           });
    }

    public class notesPreviewAdapter extends ArrayAdapter<HashMap> {

        private List<HashMap> myList;
        private int resource;
        private LayoutInflater inflater;

        public notesPreviewAdapter(Context context, int resource, List<HashMap> object) {
            super(context, resource, object);
            this.resource = resource;
            this.myList = object;
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView == null) {
                convertView = inflater.inflate(R.layout.note_preview, null);
            }

            ImageView checkedNoteIcon;
            TextView notePreview_title;
            TextView notePreview_date;

            checkedNoteIcon = (ImageView) findViewById(R.id.checkedNoteIcon);
            notePreview_title = (TextView) findViewById(R.id.notePreview_title);
            notePreview_date = (TextView) findViewById(R.id.notePreview_date);

            notePreview_title.setText("This will be from HashMap, the string");
            notePreview_title.setText("This will be from HashMap, the pair");

            return convertView;
        }
    }
}
