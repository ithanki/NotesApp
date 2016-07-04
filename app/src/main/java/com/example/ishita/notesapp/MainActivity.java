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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Pair> myDataArray = new ArrayList<Pair>();
    private ListView lv;
    private int prevLongClicked;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.listview);

        /* WE SHOULD CALL A FUNCTION TO POPULATE ARRAYLIST */
        // ** Can we use a main file to list title/date, would be easier than reading directory
        for(int i=0; i<10; i++) {
            myDataArray.add(new Pair("A: "+i, "B: "+ (-i)));
        }
        /* ABOVE SHOULD BE FROM DIRECTORY READING OR FILE READING */

        MyAdapter myAdapter=new MyAdapter(this, R.layout.note_preview, myDataArray);
        lv.setAdapter(myAdapter);
        clickActions();
    }

    public void clickActions() {
        if(myAdapter == null)
            myAdapter = (MyAdapter) lv.getAdapter();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                myDataArray.get(position).flipC();
                myAdapter.notifyDataSetChanged();
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                if(prevLongClicked != pos) {
                    Iterator<Pair> myDataIt = myDataArray.iterator();
                    while (myDataIt.hasNext()) {
                        Pair p = myDataIt.next();
                        if (p.getLP())
                            p.flipL();
                    }
                }
                myDataArray.get(pos).flipL();
                myAdapter.notifyDataSetChanged();
                prevLongClicked = pos;
                return true;
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = new Intent(getApplicationContext(), NotesActivity.class);
        int id = item.getItemId();

        switch (id) {
            case R.id.menu_new:
                i.putExtra("OPTION","NEW");
                startActivity(i);
                return true;
            case R.id.menu_delete:
                deleteNotes();
                myAdapter.notifyDataSetChanged();
                return true;
            case R.id.menu_edit:
                i.putExtra("OPTION","EDIT");
                for(Pair p : myDataArray) {
                    if(p.getLP()) {
                        i.putExtra("FILE", p.getL() + "_" + p.getR());
                        break;
                    }
                }
                startActivity(i);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void deleteNotes() {
        List<Pair> remSelected = new ArrayList<Pair>();
        for (int i = 0; i <  myDataArray.size(); i++) {
            if(myDataArray.get(i).getC())
                remSelected.add(myDataArray.get(i));
        }

        for(Pair p : remSelected)
            myDataArray.remove(p);
    }

    public void editNote() {
        //Call the editNote activity, use myDataArray.getL() ?
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

            if(objects.get(position).getC()) {
                if(objects.get(position).getLP()) objects.get(position).flipL();
                checkedNoteIcon.setImageResource(R.drawable.notechecked_icon);
            }
            else if(objects.get(position).getLP()) {
                if(objects.get(position).getC()) objects.get(position).flipC();
                checkedNoteIcon.setImageResource(R.drawable.notelongchecked_icon);
            }
            else
                checkedNoteIcon.setImageResource(R.drawable.noteunchecked_icon);

            notePreview_title.setText(objects.get(position).getL());
            notePreview_date.setText(objects.get(position).getR());

            return row;
        }
    }
}