package com.uksusoff.rock63;

import java.io.IOException;
import java.net.URL;

import com.uksusoff.rock63.utils.IcyStreamMeta;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.TrackInfo;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;

public class RadioPlayingService extends Service {
    
    public static final String RADIO_URL = "http://vzradio.ru:8000/onair?type=.mp3";
    
    //private static RadioPlayingService instance = null;
    private static boolean mRunning = false;
    private MediaPlayer mediaPlayer;
    private Float lastVolume = null;
    
    /*public static RadioPlayingService getInstance() {
        return instance;
    }*/
    
    public static boolean isServiceRunning() {
        return mRunning;
    }
    
    public RadioPlayingService() {
    }
    
    public class RadioBinder extends Binder {
        RadioPlayingService getService() {
            return RadioPlayingService.this;
        }
    }
    
    private final IBinder mBinder = new RadioBinder();

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return mBinder;
    }
    
    @Override
    public void onCreate () {
        super.onCreate();
        
        mRunning = true;
    }
    
    @Override
    public void onDestroy () {
        
        mRunning = false;
        
        super.onDestroy();
    }
        
    public Float getLastVolume() {
        return lastVolume;
    }
    
    public int onStartCommand(Intent intent, int flags, int startId) {
        
        initMediaPlayer();
        
        return START_STICKY;
    }
    
    private void initMediaPlayer() {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer.start();
            }
        });
    }
    
    public boolean isStreamPlaying() {
        return mediaPlayer.isPlaying();
    }
    
    public void setStreamVolume(float v) {
        lastVolume = v;
        mediaPlayer.setVolume(v, v);
    }
        
    public void playStream() {

        try {
            if (!mediaPlayer.isPlaying()) {
                mediaPlayer.setDataSource(RADIO_URL);
                mediaPlayer.prepareAsync();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopStream() {
                
        mediaPlayer.stop();
        mediaPlayer.reset();
    }
}