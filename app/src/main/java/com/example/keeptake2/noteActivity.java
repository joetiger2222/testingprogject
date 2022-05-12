package com.example.keeptake2;

import static com.example.keeptake2.FontSizeCustomDialog.font;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;


public class noteActivity extends AppCompatActivity {
    public static int newNoteColor=0;
    public static EditText writingNoteTitle;
    public static EditText writingNote;
    public static ConstraintLayout noteActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        writingNoteTitle=findViewById(R.id.writingNoteTitle);
        writingNote=findViewById(R.id.writingNote);
        noteActivity=findViewById(R.id.noteActivity);
    }


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
                FontSizeCustomDialog fontSizeCustomDialog=new FontSizeCustomDialog(noteActivity.this);
                fontSizeCustomDialog.show();
                break;
            case R.id.backGroundColor:
                BackGroundColor backGroundColor=new BackGroundColor(noteActivity.this);
                backGroundColor.show();
                break;
        }
        return true;
    }


    public static void changeFontSize(int fontSize){writingNote.setTextSize(fontSize);}//to the person who will take font size class



    public static void changeWritingNoteColor(int colorId){// to person who will take background class
        switch (colorId){
            case 0:
                noteActivity.setBackgroundColor(Color.BLUE);
                break;
            case 1:
                noteActivity.setBackgroundColor(Color.RED);
                break;
            case 2:
                noteActivity.setBackgroundColor(Color.YELLOW);
                break;
            case 3:
                noteActivity.setBackgroundColor(Color.WHITE);
                break;
        }
    }


    
    @Override
    public void onBackPressed() {
        newNoteColor = ((ColorDrawable)noteActivity.getBackground()).getColor();
        try {
            SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("WritingNoteDB", MODE_PRIVATE, null);
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS noteTable(title VARCHAR,noteText VARCHAR,noteFontSize int,noteBackGround int)");
            sqLiteDatabase.execSQL("INSERT INTO noteTable (title , noteText , noteFontSize , noteBackGround) values (" +"'"+writingNoteTitle.getText() +"'"+" , " +"'"+writingNote.getText()+"'"+ " , "+"'"+ font +"'"+" , "+"'"+newNoteColor+"'"+")");
            sqLiteDatabase.close();
        }catch(Exception e){e.printStackTrace();}
        MainActivity.writingNotesList.add(writingNoteTitle.getText().toString());
            MainActivity.arrayAdapter.notifyDataSetChanged();
            super.onBackPressed();

    }
}


