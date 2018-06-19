package com.rish.tictactoe;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
// 0:yellow, 1:red

    int player=0;
    int[] gamestate={2,2,2,2,2,2,2,2,2,};
    int[][] winnings= {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameactive=true;


    public void drop(View view) {

        Button playagain=(Button)(findViewById(R.id.playagain));
        TextView winnertext=(TextView)(findViewById(R.id.winnertext));
        ImageView selected = (ImageView) view;
        int tappedcounter = Integer.parseInt(selected.getTag().toString());
        String winner="";
        boolean checkdraw=true;


        if (gamestate[tappedcounter] == 2&& gameactive) {
            if (player == 0) {
                gamestate[tappedcounter] = 0;
                selected.setImageResource(R.drawable.yellow);
                player = 1;
            } else {
                gamestate[tappedcounter] = 1;
                selected.setImageResource(R.drawable.red);
                player = 0;
            }
            selected.animate().alpha(1).rotation(3600).setDuration(500);

            for (int[] winning : winnings) {
                if (gamestate[winning[0]] == gamestate[winning[1]] && gamestate[winning[1]] == gamestate[winning[2]] && gamestate[winning[2]] != 2) {
                    gameactive=false;
                        if(player==1) {

                            winner = "Player Yellow Won";
                            winnertext.setTextColor(Color.parseColor("#f2e610"));
                        }
                            else {
                            winner = "Player Red Won";
                            winnertext.setTextColor(Color.parseColor("#f71504"));

                        }



                    playagain.setVisibility(View.VISIBLE);
                    winnertext.setVisibility(View.VISIBLE);
                    checkdraw=false;

                }
            }
            if(gamestate[0]!=2&&gamestate[1]!=2&&gamestate[2]!=2&&gamestate[3]!=2&&gamestate[4]!=2&&gamestate[5]!=2&&gamestate[6]!=2&&gamestate[7]!=2&&gamestate[8]!=2&& checkdraw){
                gameactive=false;
                playagain.setVisibility(View.VISIBLE);
                winnertext.setVisibility(View.VISIBLE);
                winner="Draw Game";
                winnertext.setTextColor(Color.parseColor("#000200"));



            }
            winnertext.setText(winner);



        }
    }

    public void playagain(View view){
        Button playagain=(Button)(findViewById(R.id.playagain));
        TextView winnertext=(TextView)(findViewById(R.id.winnertext));
        playagain.setVisibility(View.INVISIBLE);
        winnertext.setVisibility(View.INVISIBLE);

        GridLayout gridLayout=(GridLayout) (findViewById(R.id.grid));

        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
            counter.animate().alpha(0);
        }




        for(int i=0;i<gamestate.length;i++) {

            gamestate[i] = 2;
        }

        player=0;

        gameactive=true;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
