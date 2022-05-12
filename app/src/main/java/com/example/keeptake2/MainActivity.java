package com.example.keeptake2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    public static MenuItem delete;
    ListView listView;
    static ArrayList<String> writingNotesList=new ArrayList<>();
    public static LinkedList<Integer> grayLinkedList=new LinkedList<>();
    public static ArrayAdapter arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listView);
        arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,writingNotesList);
        listView.setAdapter(arrayAdapter);

        if(writingNotesList.isEmpty()) {

            try {
                SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("WritingNoteDB", MODE_PRIVATE, null);
                sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS noteTable(title VARCHAR,noteText VARCHAR,noteFontSize int,noteBackGround int)");
                Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM noteTable", null);
                cursor.moveToFirst();
                int titleIndex = cursor.getColumnIndex("title");
                while (cursor != null) {
                    writingNotesList.add(cursor.getString(titleIndex));
                    cursor.moveToNext();

                }
                sqLiteDatabase.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(grayLinkedList.isEmpty()) {
                    Intent intent=new Intent(getApplicationContext(),recentNoteActivity.class);
                    intent.putExtra("noteId",i);
                    startActivity(intent);
                }
                else{
                    if(grayLinkedList.contains(i)){
                        int index=grayLinkedList.indexOf(i);
                        grayLinkedList.remove(index);
                        listView.getChildAt(i).setBackgroundColor(Color.WHITE);
                    }
                    else {
                        grayLinkedList.add(i);
                        listView.getChildAt(i).setBackgroundColor(Color.GRAY);
                    }
                    delete.setVisible(true);
                    if(grayLinkedList.isEmpty()) delete.setVisible(false);
                }
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(grayLinkedList.contains(i)){
                    int index=grayLinkedList.indexOf(i);
                    grayLinkedList.remove(index);
                    listView.getChildAt(i).setBackgroundColor(Color.WHITE);
                }
                else {
                    grayLinkedList.add(i);
                    listView.getChildAt(i).setBackgroundColor(Color.GRAY);
                }
                if(!grayLinkedList.isEmpty()) delete.setVisible(true);
                else delete.setVisible(false);
                return true;}});
    }



    public void OpenNewNote(View view){
        Intent intent=new Intent(getApplicationContext(),noteActivity.class);
        startActivity(intent);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        delete =menu.getItem(0);
        delete.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete: for(int i=0;i<grayLinkedList.size();i++){
                willbeDeleted.add(writingNotesList.get(grayLinkedList.get(i)));
            }
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("delete alert")
                        .setMessage("You are about to delete "+grayLinkedList.size()+" elements are you sure?")
                        .setCancelable(true)
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {}})
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("WritingNoteDB", MODE_PRIVATE, null);
                                    sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS noteTable(title VARCHAR,noteText VARCHAR,noteFontSize int,noteBackGround int)");
                                    for(int i=0;i<willbeDeleted.size();i++) {
                                        String delete = "DELETE FROM noteTable WHERE title = " + "'"+willbeDeleted.get(i)+ "'";
                                        sqLiteDatabase.execSQL(delete);
                                    }
                                    sqLiteDatabase.close();
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                                for(int i=0;i<willbeDeleted.size();i++){writingNotesList.remove(willbeDeleted.get(i));}
                                arrayAdapter.notifyDataSetChanged();
                                for(int i=0;i<grayLinkedList.size();i++){
                                    if(grayLinkedList.get(i)<writingNotesList.size()){
                                        listView.getChildAt(grayLinkedList.get(i)).setBackgroundColor(Color.WHITE);
                                    }
                                }
                                grayLinkedList.clear();
                                willbeDeleted.clear();
                                delete.setVisible(false);
                            }}).show();
        }
        return true;
    }
    public LinkedList<String>willbeDeleted=new LinkedList<>();



    @Override
    public void onBackPressed() {
        if(grayLinkedList.isEmpty()) super.onBackPressed();
        else{
            for(int i=0;i<grayLinkedList.size();i++){
                if(grayLinkedList.get(i)<writingNotesList.size()){
                    listView.getChildAt(grayLinkedList.get(i)).setBackgroundColor(Color.WHITE);
                }
            }
            delete.setVisible(false);
            grayLinkedList.clear();
            willbeDeleted.clear();
        }
    }
}
