package com.bignerdranch.android.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Welcome extends AppCompatActivity implements View.OnClickListener {

    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initComponent();
        
        startButton.setOnClickListener(this);

    }

    private void initComponent() {
        startButton = findViewById(R.id.start_btn);
    }


    @Override
    public void onClick(View view) {
        Button clickedButton = (Button) view;
        int clickedButtonId = clickedButton.getId();

        if (clickedButtonId == R.id.start_btn) {
            startClicked();
        }
    }

    private void startClicked() {
        Intent intent = new Intent(Welcome.this, GameStart.class);
        startActivity(intent);
        finish();
    }
}