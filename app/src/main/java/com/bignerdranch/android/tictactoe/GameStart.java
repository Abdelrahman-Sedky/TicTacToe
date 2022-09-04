package com.bignerdranch.android.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class GameStart extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout xPlayerEditText;
    private TextInputLayout oPlayerEditText;
    private Button doneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_start);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);


        initComponent();

        doneButton.setOnClickListener(this);

    }

    private void initComponent(){
        xPlayerEditText = findViewById(R.id.x_player_tf);
        oPlayerEditText = findViewById(R.id.o_player_tf);
        doneButton = findViewById(R.id.done_button);
    }


    @Override
    public void onClick(View view) {
        Button clickedButton = (Button) view;
        int clickedButtonId = clickedButton.getId();

        if (clickedButtonId == R.id.done_button) {
            doneClicked();
        }
    }

    private void doneClicked() {
        String xPlayerName = Objects.requireNonNull(xPlayerEditText.getEditText()).getText().toString();
        String oPlayerName = Objects.requireNonNull(oPlayerEditText.getEditText()).getText().toString();

        if (xPlayerName.isEmpty() && oPlayerName.isEmpty()) {
            xPlayerName = (String) getResources().getText(R.string.playerX);
            oPlayerName = (String) getResources().getText(R.string.playerO);
        } else if (xPlayerName.isEmpty()) {
            xPlayerName = (String) getResources().getText(R.string.playerX);
        } else if (oPlayerName.isEmpty()) {
            oPlayerName = (String) getResources().getText(R.string.playerO);
        }

        Intent intent = new Intent(GameStart.this, Game.class);
        intent.putExtra("NAMES", new String[]{xPlayerName, oPlayerName});
        startActivity(intent);
    }
}