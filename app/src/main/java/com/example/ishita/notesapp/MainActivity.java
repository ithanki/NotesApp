package com.example.ishita.notesapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log; //COOL LIBRARY TO DISPLAY ON ANDROID MONITOR
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.listview);

        /* WE SHOULD CALL A FUNCTION TO POPULATE ARRAYLIST */
        final ArrayList<Pair> myDataArray = new ArrayList<Pair>();
        for(int i=0; i<10; i++) {
            myDataArray.add(new Pair("A: "+i, "B: "+ (-i)));
        }
        /* ABOVE SHOULD BE FROM DIRECTORY READING OR FILE READING */

        final MyAdapter myAdapter=new MyAdapter(this, R.layout.note_preview, myDataArray);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                myDataArray.get(position).flipC();
                myAdapter.notifyDataSetChanged();
            }
        });
        lv.setAdapter(myAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.menu_new:
                startActivity(new Intent(getApplicationContext(), NotesActivity.class));
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

    public class MyAdapter extends ArrayAdapter<Pair> {
        private Context context;
        private int resource;
        private ArrayList<Pair> objects;

        public MyAdapter(Context context, int resource, ArrayList<Pair> objects) {
            super(context, resource, objects);
            this.context = context;
            this.resource = resource;
            this.objects = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater= ((Activity) context).getLayoutInflater();
            View row=inflater.inflate(resource,parent,false);

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.note_preview, parent, false);
            }

            ImageView checkedNoteIcon;
            TextView notePreview_title;
            TextView notePreview_date;

            checkedNoteIcon = (ImageView) row.findViewById(R.id.checkedNoteIcon);
            notePreview_title = (TextView) row.findViewById(R.id.notePreview_title);
            notePreview_date = (TextView) row.findViewById(R.id.notePreview_date);

            if(objects.get(position).getC())
                checkedNoteIcon.setImageResource(R.drawable.notechecked_icon);
            else
                checkedNoteIcon.setImageResource(R.drawable.noteunchecked_icon);

            notePreview_title.setText(objects.get(position).getL());
            notePreview_date.setText(objects.get(position).getR());

            return row;
        }
    }
}
