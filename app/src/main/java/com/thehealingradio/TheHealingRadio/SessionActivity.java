package com.thehealingradio.TheHealingRadio;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;

public class SessionActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener {

    private String title, url, thumb, artist ="";
    private TextView titlesession, artistsession, currentTiming, endTiming;
    private SeekBar seekBar;
    private ImageView rewind, play, forward, stop, sessionThumbnail;
    private MediaPlayer mediaPlayer;
    private Thread updateSeek;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);

        titlesession = findViewById(R.id.titlesession);
        artistsession = findViewById(R.id.artistsession);
        sessionThumbnail = findViewById(R.id.sessionThumbnail);
        currentTiming = findViewById(R.id.currentTiming);
        endTiming = findViewById(R.id.endTiming);
        seekBar = findViewById(R.id.seekBar);
        rewind = findViewById(R.id.rewind);
        play = findViewById(R.id.play);
        stop = findViewById(R.id.stop);
        forward = findViewById(R.id.forward);


        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        thumb = getIntent().getStringExtra("thumb");

        try {
            artist = getIntent().getStringExtra("artist");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            artistsession.setText(artist);
        }

        titlesession.setText(title);

        try {
            Picasso.get().load(thumb).into(sessionThumbnail);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mediaPlayer=new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        mediaPlayer.reset();
        try {
            mediaPlayer.setDataSource(url);
            Toast.makeText(this, "Loading session", Toast.LENGTH_SHORT).show();
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            Toast.makeText(this, "sorry bro nhi ho paya", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check for already playing
                if (mediaPlayer.isPlaying()) {
                    if (mediaPlayer != null) {
                        mediaPlayer.pause();
                        play.setImageResource(R.drawable.ic_play);
                    }
                } else {
                    if (mediaPlayer != null) {
                        if(!mediaPlayer.isPlaying()){
                        mediaPlayer.start();
                        play.setImageResource(R.drawable.ic_pause);
                    }else{
                            mediaPlayer.start();
                            play.setImageResource(R.drawable.ic_pause);
                        }
                    }
                }

            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check for already playing
                if (mediaPlayer.isPlaying()) {
                    if (mediaPlayer != null) {
                        mediaPlayer.stop();
                        seekBar.setProgress(0);
                        play.setImageResource(R.drawable.ic_play);
                    }
                }
            }
        });
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        Toast.makeText(this, "Ready to play", Toast.LENGTH_SHORT).show();
        seekBar.setMax(mediaPlayer.getDuration());
        updateSeek = new Thread(){
            @Override
            public void run() {
                try{
                    int currentPosition = 0;
                    while(currentPosition < mediaPlayer.getDuration()){
                        currentPosition = mediaPlayer.getCurrentPosition();
                        seekBar.setProgress(currentPosition);
                        sleep(800);
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        updateSeek.start();
        mediaPlayer.start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
        updateSeek.interrupt();
    }
}