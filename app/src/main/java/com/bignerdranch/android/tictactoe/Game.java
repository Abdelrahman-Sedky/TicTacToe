package com.bignerdranch.android.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Game extends AppCompatActivity implements View.OnClickListener {

    private TextView xPlayerNameTextView;
    private TextView oPlayerNameTextView;
    private TextView xPlayerScoreTextView;
    private TextView oPlayerScoreTextView;
    private TextView winnerTextView;
    private Button button0, button1, button2, button3, button4,
            button5, button6, button7, button8, resetBtn, clearBoardBtn;

    private ArrayList<String> board = new ArrayList<>();

    private boolean xSymbolFlag = true;

    private int xPlayerScore = 0;
    private int oPlayerScore = 0;
    private int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        String[] names = this.getIntent().getExtras().getStringArray("NAMES");

        initComponent();
        xPlayerNameTextView.setText(names[0]);
        oPlayerNameTextView.setText(names[1]);

        xPlayerScoreTextView.setText("0");
        oPlayerScoreTextView.setText("0");

        oPlayerNameTextView.setTextColor(getResources().getColor(R.color.charcoal_light));
        oPlayerScoreTextView.setTextColor(getResources().getColor(R.color.charcoal_light));

        initBoard();
    }

    public void initComponent() {
        xPlayerNameTextView = findViewById(R.id.game_x_player_tv);
        oPlayerNameTextView = findViewById(R.id.game_o_player_tv);
        xPlayerScoreTextView = findViewById(R.id.game_x_player_score_tv);
        oPlayerScoreTextView = findViewById(R.id.game_o_player_score_tv);
        winnerTextView = findViewById(R.id.winner_tv);

        //Buttons
        button0 = findViewById(R.id.button_0);
        button1 = findViewById(R.id.button_1);
        button2 = findViewById(R.id.button_2);
        button3 = findViewById(R.id.button_3);
        button4 = findViewById(R.id.button_4);
        button5 = findViewById(R.id.button_5);
        button6 = findViewById(R.id.button_6);
        button7 = findViewById(R.id.button_7);
        button8 = findViewById(R.id.button_8);
        resetBtn = findViewById(R.id.reset_btn);
        clearBoardBtn = findViewById(R.id.clear_board_btn);

        setClickListenerAllButtons();
    }

    private void initBoard() {
        for (int i = 0; i < 9; i++) {
            board.add(i, " ");
        }
    }

    public void setClickListenerAllButtons() {
        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        resetBtn.setOnClickListener(this);
        clearBoardBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Button clickedButton = (Button) view;
        int clickedButtonId = clickedButton.getId();

        if (clickedButtonId == R.id.reset_btn) {
            reset();
        } else if (clickedButtonId == R.id.clear_board_btn) {
            clearBoard();
        } else if (clickedButtonId == R.id.button_0) {
            playerClick(0, clickedButton);
            playerChange();
        } else if (clickedButtonId == R.id.button_1) {
            playerClick(1, clickedButton);
            playerChange();
        } else if (clickedButtonId == R.id.button_2) {
            playerClick(2, clickedButton);
            playerChange();
        } else if (clickedButtonId == R.id.button_3) {
            playerClick(3, clickedButton);
            playerChange();
        } else if (clickedButtonId == R.id.button_4) {
            playerClick(4, clickedButton);
            playerChange();
        } else if (clickedButtonId == R.id.button_5) {
            playerClick(5, clickedButton);
            playerChange();
        } else if (clickedButtonId == R.id.button_6) {
            playerClick(6, clickedButton);
            playerChange();
        } else if (clickedButtonId == R.id.button_7) {
            playerClick(7, clickedButton);
            playerChange();
        } else if (clickedButtonId == R.id.button_8) {
            playerClick(8, clickedButton);
            playerChange();
        }
    }


    private void playerClick(int index, Button clickedButton) {
        clickedButton.setEnabled(false);
        if (xSymbolFlag) {
            board.set(index, "X");
            clickedButton.setTextColor(getResources().getColor(R.color.venetian_ed));
            clickedButton.setText("X");
            checkForWin("X");
        } else {
            board.set(index, "O");
            clickedButton.setTextColor(getResources().getColor(R.color.green_blue));
            clickedButton.setText("O");
            checkForWin("O");
        }

        count++;
        xSymbolFlag = !xSymbolFlag;
    }


    private void checkForWin(String symbol) {

        //Check The Rows
        for (int i = 0; i <= 6; i += 3) {
            if (board.get(i).equals(symbol) && board.get(i + 1).equals(symbol) && board.get(i + 2).equals(symbol)) {
                winner(symbol);
                return;
            }
        }

        //Check The Columns
        for (int i = 0; i < 3; i++) {
            if (board.get(i).equals(symbol) && board.get(i + 3).equals(symbol) && board.get(i + 6).equals(symbol)) {
                winner(symbol);
                return;
            }
        }
        //Primary Diagonal
        if (board.get(0).equals(symbol) && board.get(4).equals(symbol) && board.get(8).equals(symbol)) {
            winner(symbol);
            return;
        }
        //Secondary Diagonal
        if (board.get(2).equals(symbol) && board.get(4).equals(symbol) && board.get(6).equals(symbol)) {
            winner(symbol);
            return;
        }

        //Draw
        if (count == 9) {
            winnerTextView.setTextColor(getResources().getColor(R.color.charcoal));
            winnerTextView.setText(getResources().getText(R.string.draw));
            xSymbolFlag = !xSymbolFlag;

        }

    }

    private void winner(String winnerSymbol) {
        if (winnerSymbol.equals("X")) {
            winnerTextView.setTextColor(getResources().getColor(R.color.venetian_ed));
            winnerTextView.setText(getResources().getText(R.string.winner));
            xSymbolFlag = false;
        } else {
            winnerTextView.setTextColor(getResources().getColor(R.color.green_blue));
            winnerTextView.setText(getResources().getText(R.string.winner));
            xSymbolFlag = true;
        }

        winState();
        updateScore(winnerSymbol);
    }

    private void updateScore(String winnerSymbol) {
        if (winnerSymbol.equals("X")) {
            xPlayerScore++;
        } else {
            oPlayerScore++;
        }
        xPlayerScoreTextView.setText(String.valueOf(xPlayerScore));
        oPlayerScoreTextView.setText(String.valueOf(oPlayerScore));
    }

    private void playerChange() {
        if (xSymbolFlag) {
            xToPlay();
        } else {
            oToPlay();
        }
    }

    private void oToPlay() {
        oPlayerNameTextView.setTextColor(getResources().getColor(R.color.green_blue));
        oPlayerScoreTextView.setTextColor(getResources().getColor(R.color.green_blue));
        xPlayerNameTextView.setTextColor(getResources().getColor(R.color.charcoal_light));
        xPlayerScoreTextView.setTextColor(getResources().getColor(R.color.charcoal_light));
    }

    private void xToPlay() {
        xPlayerNameTextView.setTextColor(getResources().getColor(R.color.venetian_ed));
        xPlayerScoreTextView.setTextColor(getResources().getColor(R.color.venetian_ed));
        oPlayerNameTextView.setTextColor(getResources().getColor(R.color.charcoal_light));
        oPlayerScoreTextView.setTextColor(getResources().getColor(R.color.charcoal_light));
    }

    private void clearBoard() {
        button0.setText(null);
        button0.setEnabled(true);

        button1.setText(null);
        button1.setEnabled(true);

        button2.setText(null);
        button2.setEnabled(true);

        button3.setText(null);
        button3.setEnabled(true);

        button4.setText(null);
        button4.setEnabled(true);

        button5.setText(null);
        button5.setEnabled(true);

        button6.setText(null);
        button6.setEnabled(true);

        button7.setText(null);
        button7.setEnabled(true);

        button8.setText(null);
        button8.setEnabled(true);

        count = 1;
        board.clear();
        initBoard();
    }

    private void reset() {
        xPlayerScoreTextView.setText("0");
        oPlayerScoreTextView.setText("0");
        winnerTextView.setText(null);
        xPlayerScore = 0;
        oPlayerScore = 0;
        clearBoard();
    }

    private void winState() {
        button0.setEnabled(false);
        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
        button4.setEnabled(false);
        button5.setEnabled(false);
        button6.setEnabled(false);
        button7.setEnabled(false);
        button8.setEnabled(false);
    }
}