package com.example.keeptake2;

import static com.example.keeptake2.fontSizeDialogRcntNote.font;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

public class recentNoteActivity extends AppCompatActivity {
    private static int recentNoteColor =0;
    public static EditText title,recentNote;
    public  static ConstraintLayout recentNoteLayout;
    public int noteId;
    public String noteTitleString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_note);
        recentNoteLayout=findViewById(R.id.recentNoteActivity);
        title =findViewById(R.id.recentNoteTitle);
        recentNote=findViewById(R.id.recentNote);
        try {
            SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("WritingNoteDB", MODE_PRIVATE, null);
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS noteTable(title VARCHAR,noteText VARCHAR,noteFontSize int,noteBackGround int)");
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM noteTable", null);
            cursor.moveToFirst();
            int titleIndex = cursor.getColumnIndex("title");
            int noteTextIndex=cursor.getColumnIndex("noteText");
            int noteFontSizeIndex=cursor.getColumnIndex("noteFontSize");
            int noteBackGroundColorIndex=cursor.getColumnIndex("noteBackGround");
            while (cursor != null) {
                Intent intent=getIntent();
                noteId= intent.getIntExtra("noteId",-1);
                if(noteId!=-1) {
                    noteTitleString =MainActivity.writingNotesList.get(noteId);
                }else noteTitleString ="error happened";
                if((noteTitleString).equals(cursor.getString(titleIndex))){
                    recentNote.setText(cursor.getString(noteTextIndex));
                    font =cursor.getInt(noteFontSizeIndex);
                    recentNote.setTextSize(font);
                    recentNoteLayout.setBackgroundColor(cursor.getInt(noteBackGroundColorIndex));
                    break;
                }
                cursor.moveToNext();
            }
            sqLiteDatabase.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        Intent intent=getIntent();
        noteId= intent.getIntExtra("noteId",-1);
        if(noteId!=-1) {
            noteTitleString =MainActivity.writingNotesList.get(noteId);
        }else noteTitleString ="error happened";
        title.setText(noteTitleString);
    }




    public static void changefont(int i){recentNote.setTextSize(i);}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.note_options_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.fontSizeSeekBar:
                fontSizeDialogRcntNote fontSizeDialogRcntNote=new fontSizeDialogRcntNote(recentNoteActivity.this);
                fontSizeDialogRcntNote.show();
                break;
            case R.id.backGroundColor:recentNoteBackGroundColor recentNoteBackGroundColor=new recentNoteBackGroundColor(recentNoteActivity.this);
            recentNoteBackGroundColor.show();

        }
        return true;
    }





    public static void changeWritingNoteColor(int colorId){// to person who will take background class
        switch (colorId){
            case 0:
                recentNote.setBackgroundColor(Color.BLUE);
                break;
            case 1:
                recentNote.setBackgroundColor(Color.RED);
                break;
            case 2:
                recentNote.setBackgroundColor(Color.YELLOW);
                break;
            case 3:
                recentNote.setBackgroundColor(Color.WHITE);
                break;
        }
    }



    @Override
    public void onBackPressed() {
        recentNoteColor = ((ColorDrawable)recentNoteLayout.getBackground()).getColor();
        try {
            SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("WritingNoteDB", MODE_PRIVATE, null);
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS noteTable(title VARCHAR,noteText VARCHAR,noteFontSize int,noteBackGround int)");
            String update = "UPDATE noteTable SET title = " +"'"+ title.getText().toString()+"'" + " , noteText = "+"'" + recentNote.getText().toString()+"'"+" , noteFontSize = "+"'"+ font +"'"+", noteBackGround = "+"'"+ recentNoteColor +"'" + " WHERE title = " +"'"+noteTitleString+"'";
            sqLiteDatabase.execSQL(update);
            sqLiteDatabase.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        super.onBackPressed();
    }
}
