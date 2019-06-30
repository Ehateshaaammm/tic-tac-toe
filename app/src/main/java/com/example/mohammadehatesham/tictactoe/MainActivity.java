package com.example.mohammadehatesham.tictactoe;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //1:x 0:o 2:empty

    int start = 1;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive = true;
    public void drop(View view){

        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter]==2 && gameActive) {
        gameState[tappedCounter]=start;
            counter.setTranslationY(-1500);
            if (start == 1) {
                counter.setImageResource(R.drawable.x);
                start = 0;
            } else {
                counter.setImageResource(R.drawable.o);
                start = 1;
            }

            counter.animate().translationYBy(1500).rotationBy(1800).setDuration(800);
            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    //someone has won
                    gameActive = false;
                    String msg;
                    if (start == 1) {
                        msg = "O has won";
                    } else {
                        msg = "X has won";
                    }
                    TextView textView = (TextView) findViewById(R.id.textView);
                    Button button = (Button) findViewById(R.id.button);
                    textView.setText(msg);
                    textView.setVisibility(View.VISIBLE);
                    button.setVisibility(View.VISIBLE);
                }
           }


        }
    }

    public void playAgain(View view){
        TextView textView = (TextView) findViewById(R.id.textView);
        Button button = (Button) findViewById(R.id.button);
        textView.setVisibility(View.INVISIBLE);
        button.setVisibility(View.INVISIBLE);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
       for(int i=0;i<gridLayout.getChildCount();i++){
           ImageView counter = (ImageView) gridLayout.getChildAt(i);
           counter.setImageDrawable(null);
       }
        start = 1;
       for(int i =0;i<gameState.length;i++) {
       gameState[i]=2;
       }
        gameActive = true;
    }

    public void exit(View view){
        finish();
        System.exit(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
