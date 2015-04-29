package com.example.dikons.shitweb;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;


public class NextActivity extends ActionBarActivity {

    private VideoView myVideoView;
    private int position = 3;
    private ProgressDialog progressDialog;
    private MediaController mediaControls;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set the main layout of the activity
        setContentView(R.layout.activity_next);

        //set media controller buttons
        if(mediaControls == null){
            mediaControls = new MediaController(NextActivity.this);
        }

        //initialize videoView
        myVideoView = (VideoView) findViewById(R.id.videoView);

        //create the progress bar while video is loading
        progressDialog = new ProgressDialog(NextActivity.this);
        //set the title for progress bar
        progressDialog.setTitle("Dlaczego tak niekulturalnie?");
        //set message for the progress bar
        progressDialog.setMessage("Czekaj psie...");
        //set the progress bar not cancelable
        progressDialog.setCancelable(false);
        //show progress bar
        progressDialog.show();

        try{
            //set the media controller in the VideoView
            myVideoView.setMediaController(mediaControls);
            //set the uri of the video to be played
            myVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.siara));

        } catch (Exception e){
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        myVideoView.requestFocus();

        //also set onPreparedListener to know when the video file is ready for playback
        myVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //close the progress bar and play video
                progressDialog.dismiss();
                //if we have saved position, video playback starts from here
                myVideoView.seekTo(position);
                if(position == 0 ){
                    myVideoView.start();
                } else myVideoView.pause();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
       super.onSaveInstanceState(savedInstanceState);

        //we use onSaveInstanceState in order to store the video playback position for orientation change
        savedInstanceState.putInt("Position", myVideoView.getCurrentPosition());
        myVideoView.pause();

    }



    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        //we use onRestoreInstanceState in order to play the video playback from the stored position
        position = savedInstanceState.getInt("Position");
        myVideoView.seekTo(position);

    }



    public void onNext(View view) {
        Intent intent = new Intent(this, TutorialActivity.class);
        startActivity(intent);
    }
}
