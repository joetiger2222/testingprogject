package com.example.keeptake2;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

public class BackGroundColor extends Dialog  {
    public Activity c;
    public Dialog d;
    ImageView blueCircle;
    ImageView redCircle;
    ImageView orangeCircle;
    ImageView whiteCircle;
    public BackGroundColor(Activity a) {
        super(a);
        this.c=a;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.background_color_custom_dialog);
        whiteCircle=findViewById(R.id.whiteCircle);
        blueCircle=findViewById(R.id.blueCircle);
        redCircle=findViewById(R.id.redCircle);
        orangeCircle=findViewById(R.id.orangeCircle);
        blueCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {noteActivity.changeWritingNoteToBlue();}});
        redCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {noteActivity.changeWritingNoteToRed();}});
        orangeCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {noteActivity.changeWritingNoteToOrange();}});

        whiteCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {noteActivity.changeWritingNoteToWhite();}});
    }






}
