package com.example.tictactoe2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button[][] buttons = new Button[3][3];//button array to check for win later
    private boolean player1turn=true;//turn control
    private int roundCount;//to count turns played
    private int player1points;
    private int player2points;
    private TextView textViewPlayer1;
    private TextView textViewPlayer2;
    String A="X";
    String B="O";
    String C;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewPlayer1 = findViewById(R.id.player1score);
        textViewPlayer2 = findViewById(R.id.player2score);
        for (int i=0;i<3;i++)//connecting the buttons' ids to the created button array
        {
            for(int j=0;j<3;j++) {
                String buttonID = "b" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id",getPackageName());
                buttons[i][j]=findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }
        Button buttonReset=findViewById(R.id.buttonreset);
        //Response on clicking Reset button
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player1points=0;
                player2points=0;
                updatePointsText();
                resetBoard();
                player1turn=true;
                A="X";
                B="O";


            }
        });



    }

    @Override
    public void onClick(View v) //gameplay input, i.e. to enter X or Y into the button on clicking buttons
    {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        if (player1turn) {
            ((Button) v).setText(A);
        } else {
            ((Button) v).setText(B);
        }
        roundCount++;
        if(checkForWin())
        {if (player1turn)
        {player1Wins();}
        else
            player2Wins();
        }else if (roundCount==9)
        {draw();}
        else
        {player1turn = !player1turn;}


    }



    private boolean checkForWin()// checking for win
    {
        String[][] field= new String[3][3];
        for (int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                field[i][j]=buttons[i][j].getText().toString();//getting the text from the button
            }
        }
        for (int i=0;i<3;i++)//row check
        {
            if (field[i][0].equals(field[i][1])&&field[i][0].equals(field[i][2])&&!field[i][0].equals(""))
            {return true;}

        }
        for (int i=0;i<3;i++)//column check
        {
            if (field[0][i].equals(field[1][i])&&field[0][i].equals(field[2][i])&&!field[0][i].equals(""))
            {return true;}

        }

        if (field[0][0].equals(field[1][1])&&field[0][0].equals(field[2][2])&&!field[0][0].equals(""))//diagonal 1 check
        {return true;}
        if (field[0][2].equals(field[1][1])&&field[0][2].equals(field[2][0])&&!field[0][2].equals(""))//diagonal 2 check
        {return true;}
        return false;


    }
    private void player1Wins()//in case of player 1's win
    {
        player1points++;
        Toast.makeText( this, "Player 1 Wins", Toast.LENGTH_SHORT).show();
        resetBoard();
        switchturn();
        updatePointsText();
    }


    private void player2Wins()//in case of player 2's win
    {player2points++;
        Toast.makeText( this, "Player 2 Wins", Toast.LENGTH_SHORT).show();
        resetBoard();
        switchturn();
        updatePointsText();

    }
    private void draw() //in case of Draw
    {Toast.makeText(this,"Draw!",Toast.LENGTH_SHORT).show();
        resetBoard();
        switchturn();
        updatePointsText();

    }
    private void updatePointsText() //updating the scoreboard
    {
        textViewPlayer1.setText(A+"-Player 1: " + player1points);
        textViewPlayer2.setText(B+"-Player 2: " + player2points);
    }

    private void resetBoard()//resetting the board
    {
        for (int i=0;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {
                buttons[i][j].setText("");
            }
        }
        roundCount=0;

    }
    private void switchturn()//switching turns after each round
    {
        C=A;
        A=B;
        B=C;
        if(A.equals("X"))
        {player1turn=true;}
        else
        {player1turn=false;}
    }
}