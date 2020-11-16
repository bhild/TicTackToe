package com.example.tictacktoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.Console;
import java.util.stream.IntStream;

import static java.util.Arrays.stream;

public class GameActivity extends AppCompatActivity {

    private Button doneButton;
    int[][] buttonStates = new int[3][3];//creates a 3x3 array
    //0 - empty
    //1 - x
    //2 - o
    boolean xOrO = false;// if the active player is x or O
    //true - O
    //false - x
    private Button[][] gameButtons = new Button[3][3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        for (int i = 0;i<3;i++){
            for(int j = 0; j<3;j++){
                //String id = "button"+i+j;
                int id = getResources().getIdentifier("button"+i+""+j,"id",getPackageName());
                gameButtons[i][j] = (Button) findViewById(id);//id);
                final int finalI = i;
                final int finalJ = j;
                gameButtons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!xOrO){
                            buttonStates[finalI][finalJ] = 1;
                        }else{
                            buttonStates[finalI][finalJ] = 2;
                        }
                        xOrO=!xOrO;
                        gameButtons[finalI][finalJ].setText(!xOrO+"");
                        gameButtons[finalI][finalJ].setEnabled(false);
                        gameWon(); //check for game won
                    }
                });
            }
        }
        doneButton = (Button) findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDoneActivty();
            }
        });
    }
    public void openDoneActivty() {
        Intent intent4 = new Intent(this, MainActivity.class);
        startActivity(intent4);
    }
    private boolean isCatsGame(){
        boolean returnState = true;
        boolean gameWon = false;
        for (int i = 0; i<3;i++){
            for(int j = 0; j<3;j++){
                if(buttonStates[i][j]==0&&returnState) {
                    returnState = false;
                }
            }
        }
        //detect is game is won by calling Nathans method here
        return returnState&&!gameWon;
    }
    private boolean gameWon(){
        boolean returnState = false;
        for(int x = 0; x < 3; x++)//check for vertical wins
        {
            if (buttonStates[x][0] == buttonStates[x][1] && buttonStates[x][1] == buttonStates[x][2] && buttonStates[x][0] != 0) {
                returnState = true;
            }
        }
        for(int y = 0; y < 3; y++)//check for horizontal wins
        {
            if (buttonStates[0][y] == buttonStates[1][y] && buttonStates[1][y] == buttonStates[2][y] && buttonStates[0][y] != 0) {
                returnState = true;
            }
        }
        //check for diagonal wins
        if(buttonStates[0][0] == buttonStates[1][1] && buttonStates[1][1] == buttonStates[2][2] && buttonStates[0][0] != 0)
        {
            returnState = true;
        }
        else if(buttonStates[0][2] == buttonStates[1][1] && buttonStates[1][1] == buttonStates[2][0] && buttonStates[0][2] != 0)
        {
            returnState = true;
        }
        return returnState;
    }
}