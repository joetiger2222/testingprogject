package com.example.keeptake2;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import androidx.annotation.NonNull;

public class recentNoteBackGroundColor extends Dialog {
    public Activity c;
    public Dialog d;
    ImageView blueCircle;
    ImageView redCircle;
    ImageView orangeCircle;
    ImageView whiteCircle;
    public recentNoteBackGroundColor(Activity a) {
        super(a);
        this.c=a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.background_color_custom_dialog);
        blueCircle=findViewById(R.id.blueCircle);
        whiteCircle=findViewById(R.id.whiteCircle);
        redCircle=findViewById(R.id.redCircle);
        orangeCircle=findViewById(R.id.orangeCircle);
        blueCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {recentNoteActivity.changeWritingNoteColor(0);}});
        redCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {recentNoteActivity.changeWritingNoteColor(1);}});
        orangeCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {recentNoteActivity.changeWritingNoteColor(2);}});
        whiteCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recentNoteActivity.changeWritingNoteColor(3);}});
    }

}
