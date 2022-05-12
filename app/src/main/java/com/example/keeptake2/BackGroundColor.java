package com.example.keeptake2;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

public class BackGroundColor extends Dialog  {
    public Activity c;
    public Dialog d;
    public ImageView b;
    public ImageView r;
    public ImageView o;
    public ImageView w;
    public BackGroundColor(Activity a) {
        super(a);
        this.c=a;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.background_color_custom_dialog);
        w =findViewById(R.id.whiteCircle);
        b =findViewById(R.id.blueCircle);
        r =findViewById(R.id.redCircle);
        o =findViewById(R.id.orangeCircle);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {noteActivity.changeWritingNoteColor(0);}});
        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {noteActivity.changeWritingNoteColor(1);}});
        o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {noteActivity.changeWritingNoteColor(2);}});

        w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {noteActivity.changeWritingNoteColor(3);}});
    }

}
