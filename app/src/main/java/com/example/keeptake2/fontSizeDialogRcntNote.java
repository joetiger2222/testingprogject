package com.example.keeptake2;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

public class fontSizeDialogRcntNote extends Dialog implements View.OnClickListener {
    public Activity activity;
    public Dialog dialog;
    public static SeekBar fontSizeSeekBarRcntNote;
    public Button resetFontSizeBtnRcntNote;
    public static int rcntNoteFntSize=20;

    public fontSizeDialogRcntNote(Activity a) {
        super(a);
        this.activity=a;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.font_size_custom_dialog);
        resetFontSizeBtnRcntNote=findViewById(R.id.resetBtn);
        fontSizeSeekBarRcntNote=findViewById(R.id.fontSizeSeekBar);
        fontSizeSeekBarRcntNote.setMin(12);
        fontSizeSeekBarRcntNote.setProgress(rcntNoteFntSize);
        fontSizeSeekBarRcntNote.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                recentNoteActivity.changeRcntNoteFontSze(i);
                rcntNoteFntSize=i;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        resetFontSizeBtnRcntNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recentNoteActivity.changeRcntNoteFontSze(20);
                fontSizeSeekBarRcntNote.setProgress(20);
            }
        });
    }

    @Override
    public void onClick(View view) {}
}
