package com.example.keeptake2;

import android.app.Activity;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.annotation.RequiresApi;

public class fontSizeDialogRcntNote extends Dialog implements View.OnClickListener {
    public Activity activity;
    public Dialog dialog;
    public static SeekBar seekbar;
    public Button resetbutton;
    public static int font =20;

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
        resetbutton =findViewById(R.id.resetBtn);
        seekbar =findViewById(R.id.fontSizeSeekBar);
        seekbar.setMin(12);
        seekbar.setProgress(font);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                recentNoteActivity.changefont(i);
                font =i;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        resetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recentNoteActivity.changefont(20);
                seekbar.setProgress(20);
            }
        });
    }

    @Override
    public void onClick(View view) {}
}
