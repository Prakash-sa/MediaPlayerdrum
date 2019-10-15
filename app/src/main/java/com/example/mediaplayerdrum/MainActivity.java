package com.example.mediaplayerdrum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

import static com.example.mediaplayerdrum.R.raw.song;

public class MainActivity extends AppCompatActivity {

    MediaPlayer m1;
    AudioManager a1;
    SeekBar s1,s2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m1=MediaPlayer.create(this, song);

        a1=(AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int mymaxvalue=a1.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int mycurrent=a1.getStreamVolume(AudioManager.STREAM_MUSIC);
        SeekBar s1=findViewById(R.id.volumebar);
        final SeekBar s2=findViewById(R.id.timebar);
        s1.setMax(mymaxvalue);
        s1.setProgress(mycurrent);
        s1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                a1.setStreamVolume(AudioManager.STREAM_MUSIC,i,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        s2.setMax(m1.getDuration());
        s2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int k=0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
              if(k==1) {
                  m1.seekTo(i);
                   k = 0;

              }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                    k=1;
            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                s2.setProgress(m1.getCurrentPosition());
            }
        }, 0, 1000);

    }




    public void onPlay(View view) {
        m1.start();
    }

    public void onPause(View view) {
        m1.pause();
    }
}
