package com.kslau.nexus.tictactoegame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private Board board;
    private Cell.Content currentPlayer; // x is human //o is ai

    private Button zero;
    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private Button six;
    private Button seven;
    private Button eight;
    private Button reset;
    private TextView statusTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindUI();
        loadDefaultValue();

    }

    private void loadDefaultValue() {

        board = new Board();
        startGame();
    }

    private void startGame() {
        board.init();
        currentPlayer = Cell.Content.X;
        statusTV.setText("X 's turn");

        zero.setText("");
        one.setText("");
        two.setText("");
        three.setText("");
        four.setText("");
        five.setText("");
        six.setText("");
        seven.setText("");
        eight.setText("");

        zero.setEnabled(true);
        one.setEnabled(true);
        two.setEnabled(true);
        three.setEnabled(true);
        four.setEnabled(true);
        five.setEnabled(true);
        six.setEnabled(true);
        seven.setEnabled(true);
        eight.setEnabled(true);
    }

    private void stopGame() {
        zero.setEnabled(false);
        one.setEnabled(false);
        two.setEnabled(false);
        three.setEnabled(false);
        four.setEnabled(false);
        five.setEnabled(false);
        six.setEnabled(false);
        seven.setEnabled(false);
        eight.setEnabled(false);
    }

    private void bindUI() {

        zero = (Button) findViewById(R.id.zero);
        zero.setOnClickListener(this);
        one = (Button) findViewById(R.id.one);
        one.setOnClickListener(this);
        two = (Button) findViewById(R.id.two);
        two.setOnClickListener(this);
        three = (Button) findViewById(R.id.three);
        three.setOnClickListener(this);
        four = (Button) findViewById(R.id.four);
        four.setOnClickListener(this);
        five = (Button) findViewById(R.id.five);
        five.setOnClickListener(this);
        six = (Button) findViewById(R.id.six);
        six.setOnClickListener(this);
        seven = (Button) findViewById(R.id.seven);
        seven.setOnClickListener(this);
        eight = (Button) findViewById(R.id.eight);
        eight.setOnClickListener(this);

        reset = (Button) findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });

        statusTV = (TextView) findViewById(R.id.status);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.zero:
                board.currentRow = 0;
                board.currentCol = 0;
                if (currentPlayer == Cell.Content.X) {
                    zero.setText("X");
                    board.updatePosition(Cell.Content.X);
                } else {
                    zero.setText("O");
                    board.updatePosition(Cell.Content.O);
                }
                zero.setEnabled(false);
                break;
            case R.id.one:
                board.currentRow = 0;
                board.currentCol = 1;
                if (currentPlayer == Cell.Content.X) {
                    one.setText("X");
                    board.updatePosition(Cell.Content.X);
                } else {
                    one.setText("O");
                    board.updatePosition(Cell.Content.O);
                }
                one.setEnabled(false);
                break;
            case R.id.two:
                board.currentRow = 0;
                board.currentCol = 2;
                if (currentPlayer == Cell.Content.X) {
                    two.setText("X");
                    board.updatePosition(Cell.Content.X);
                } else {
                    two.setText("O");
                    board.updatePosition(Cell.Content.O);
                }
                two.setEnabled(false);
                break;
            case R.id.three:
                board.currentRow = 1;
                board.currentCol = 0;
                if (currentPlayer == Cell.Content.X) {
                    three.setText("X");
                    board.updatePosition(Cell.Content.X);
                } else {
                    three.setText("O");
                    board.updatePosition(Cell.Content.O);
                }
                three.setEnabled(false);
                break;
            case R.id.four:
                board.currentRow = 1;
                board.currentCol = 1;
                if (currentPlayer == Cell.Content.X) {
                    four.setText("X");
                    board.updatePosition(Cell.Content.X);
                } else {
                    four.setText("O");
                    board.updatePosition(Cell.Content.O);
                }
                four.setEnabled(false);
                break;
            case R.id.five:
                board.currentRow = 1;
                board.currentCol = 2;
                if (currentPlayer == Cell.Content.X) {
                    five.setText("X");
                    board.updatePosition(Cell.Content.X);
                } else {
                    five.setText("O");
                    board.updatePosition(Cell.Content.O);
                }
                five.setEnabled(false);
                break;
            case R.id.six:
                board.currentRow = 2;
                board.currentCol = 0;
                if (currentPlayer == Cell.Content.X) {
                    six.setText("X");
                    board.updatePosition(Cell.Content.X);
                } else {
                    six.setText("O");
                    board.updatePosition(Cell.Content.O);
                }
                six.setEnabled(false);
                break;
            case R.id.seven:
                board.currentRow = 2;
                board.currentCol = 1;
                if (currentPlayer == Cell.Content.X) {
                    seven.setText("X");
                    board.updatePosition(Cell.Content.X);
                } else {
                    seven.setText("O");
                    board.updatePosition(Cell.Content.O);
                }
                seven.setEnabled(false);
                break;
            case R.id.eight:
                board.currentRow = 2;
                board.currentCol = 2;
                if (currentPlayer == Cell.Content.X) {
                    eight.setText("X");
                    board.updatePosition(Cell.Content.X);
                } else {
                    eight.setText("O");
                    board.updatePosition(Cell.Content.O);
                }
                eight.setEnabled(false);
                break;
            default:
                Log.d(TAG, "Unknown button click");
                break;

        }

        if (board.isWon(Cell.Content.X)) {
            statusTV.setText("X is Won!");
            stopGame();
        } else if (board.isWon(Cell.Content.O)) {
            statusTV.setText("O is Won!");
            stopGame();
        } else if (board.isDraw()) {
            statusTV.setText("Game is Draw!");
            stopGame();
        } else {

            if (currentPlayer == Cell.Content.X) {
                currentPlayer = Cell.Content.O;
                statusTV.setText("O 's turn...Please wait!");

                MinMaxAlgorithm minmax = new MinMaxAlgorithm(board.cells);
                int[] results = minmax.run();
                updateAiMove(results[1], results[2]);

                Log.d(TAG, "AI results:" + Arrays.toString(results));
                Log.d(TAG, "-----------------------------------------------");
            } else {
                currentPlayer = Cell.Content.X;
                statusTV.setText("X 's turn");
            }
        }
    }


    private void updateAiMove(int row, int col) {
        if (row == 0 && col == 0) {
            zero.callOnClick();
        } else if (row == 0 && col == 1) {
            one.callOnClick();
        } else if (row == 0 && col == 2) {
            two.callOnClick();
        } else if (row == 1 && col == 0) {
            three.callOnClick();
        } else if (row == 1 && col == 1) {
            four.callOnClick();
        } else if (row == 1 && col == 2) {
            five.callOnClick();
        } else if (row == 2 && col == 0) {
            six.callOnClick();
        } else if (row == 2 && col == 1) {
            seven.callOnClick();
        } else if (row == 2 && col == 2) {
            eight.callOnClick();
        } else {

        }
    }
}
