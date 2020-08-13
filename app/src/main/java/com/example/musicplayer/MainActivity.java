package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    SeekBar seekBar;
    ImageView playImage;
    int max = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer=MediaPlayer.create(getApplicationContext(), R.raw.stuff);
        playImage= findViewById(R.id.play);
        seekBar = findViewById(R.id.lenSong);
        seekBar.setMax(mediaPlayer.getDuration());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b){
                    mediaPlayer.seekTo(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
            }
        }, 0, 1000);
    }

    public void prev(View view) {
        mediaPlayer.pause();
        playImage.setImageResource(R.drawable.ic_baseline_play_arrow_24);
        seekBar.setProgress(0);
        mediaPlayer.seekTo(0);
    }

    public void next(View view) {
        mediaPlayer.pause();
        playImage.setImageResource(R.drawable.ic_baseline_play_arrow_24);
        max = mediaPlayer.getDuration();
        seekBar.setProgress(max);
        mediaPlayer.seekTo(max);
    }

    public void play(View view) {

        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            playImage.setImageResource(R.drawable.ic_baseline_play_arrow_24);
        }
        else {
            mediaPlayer.start();
            playImage.setImageResource(R.drawable.ic_baseline_pause_24);
        }
    }
}