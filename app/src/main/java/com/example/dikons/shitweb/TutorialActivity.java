package com.example.dikons.shitweb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.example.dikons.shitweb.R;

/**
 * Created by dikons on 4/29/15.
 */
public class TutorialActivity extends ActionBarActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
    }


    public void onNoDare(View view) {
        Intent patrzPanNieByloIslandii = new Intent(this, NextActivity.class);
        startActivity(patrzPanNieByloIslandii);
    }

}

