package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button b00;Button b01;Button b02; Button b10; Button b11; Button b12; Button b20; Button b21; Button b22;
    TextView tv_info;
    Button b_reset;
    boolean Player_turn = true;
    int[][] status = new int[3][3];
    int Turn_Count =0;
    String Player_1;
    String Player_2;
    ArrayList<Integer> Phone_matrice = new ArrayList<>();
    Random random ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b00 = findViewById(R.id.b00);
        b00.setOnClickListener(this);
        b01 = findViewById(R.id.b01);
        b01.setOnClickListener(this);
        b02 = findViewById(R.id.b02);
        b02.setOnClickListener(this);

        b10 = findViewById(R.id.b10);
        b10.setOnClickListener(this);
        b11 = findViewById(R.id.b11);
        b11.setOnClickListener(this);
        b12 = findViewById(R.id.b12);
        b12.setOnClickListener(this);

        b20 = findViewById(R.id.b20);
        b20.setOnClickListener(this);
        b21 = findViewById(R.id.b21);
        b21.setOnClickListener(this);
        b22 = findViewById(R.id.b22);
        b22.setOnClickListener(this);

        b_reset = findViewById(R.id.breset);
        b_reset.setOnClickListener(this);
        tv_info = findViewById(R.id.tvinfo);

        Phone_matrice.add(b00.getId());Phone_matrice.add(b01.getId());Phone_matrice.add(b02.getId());
        Phone_matrice.add(b10.getId());Phone_matrice.add(b11.getId());Phone_matrice.add(b12.getId());
        Phone_matrice.add(b20.getId());Phone_matrice.add(b21.getId());Phone_matrice.add(b22.getId());

        recive_intent();
        reset_status();
        set_info(Player_1+" " + getString(R.string.Turn));
    }

    @Override
    public void onClick(View view) {
        boolean reset_Button = false;
        Button clicked = findViewById(view.getId());
        if(Player_turn && view.getId()!=R.id.breset){
            Phone_matrice.remove(Phone_matrice.indexOf(view.getId()));
            Player_turn=!Player_turn;
            clicked.setText("X");
            clicked.setEnabled(false);
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(status[i][j]==view.getId()){
                        status[i][j]=1;
                    }
                }
            }

        }
        else if(!Player_turn && view.getId()!=R.id.breset){
            Phone_matrice.remove(Phone_matrice.indexOf(view.getId()));
            Player_turn=!Player_turn;
            clicked.setText("0");
            clicked.setEnabled(false);
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(status[i][j]==view.getId()){
                        status[i][j]=0;
                    }
                }
            }
        }
        else if(view.getId()==R.id.breset){
            reset_Button=true;
        }


        if (reset_Button){
            rest_Board();
        }
        else {


            Turn_Count++;
            if (Player_turn) {
                set_info(Player_1 + " " + getResources().getString(R.string.Turn));
            } else {
                set_info(Player_2 + " " + getResources().getString(R.string.Turn));
            }
            if (Turn_Count == 9) {
                result("Game Draws");
            }
            else {
                check_winner();
                play(view);
            }
        }
    }

    public void set_info(String info){
        this.tv_info.setText(info);
    }
    public void check_winner(){
        //check the vertical raw
        for(int i =0;i<3;i++){
            if(status[i][0] == status[i][1] && status[i][0] == status[i][2] ){
                if (status[i][0]==1){
                    result(getString(R.string.The_winner_is)+ " " +Player_1);
                    EnableAllButton(false);
                    break;
                }
                else if (status[i][0]==0){ result(getString(R.string.The_winner_is)+ " " +Player_2);
                    EnableAllButton(false);
                    break;
                }

            }
        }
        //check the horizontal raw
        for(int i =0;i<3;i++){
            if(status[0][i] == status[1][i] && status[0][i] == status[2][i]){
                if (status[0][i]==1){
                    result(getString(R.string.The_winner_is)+ " " +Player_1);
                    EnableAllButton(false);
                    break;
                }
                else if (status[0][i]==0){ result(getString(R.string.The_winner_is)+ " " +Player_2);
                    EnableAllButton(false);
                    break;
                }


            }
        }
        //check the first diagonal
        if(status[0][0] == status[1][1] && status[0][0] == status[2][2] ){
            if (status[0][0]==1){
                result(getString(R.string.The_winner_is)+ " " +Player_1);
                EnableAllButton(false);

            }
            else if (status[0][0]==0){ result(getString(R.string.The_winner_is)+ " " +Player_2);
                EnableAllButton(false);
            }

        }

        //check the second diagonal
        if(status[0][2] == status[1][1] && status[0][2] == status[2][0] ){
            if (status[0][2]==1){
                result(getString(R.string.The_winner_is)+ " " +Player_1);
                EnableAllButton(false);
            }
            else if (status[0][2]==0){ result(getString(R.string.The_winner_is)+ " " +Player_2);
            EnableAllButton(false);}


        }

    }
    public void result(String info){
        set_info(info);

    }
    public void rest_Board(){
        Phone_matrice.clear();
        Turn_Count =0;
        Player_turn = true;
        b00.setText("");b01.setText("");b02.setText("");
        b10.setText("");b11.setText("");b12.setText("");
        b20.setText("");b21.setText("");b22.setText("");
        reset_status();
        Phone_matrice.add(b00.getId());Phone_matrice.add(b01.getId());Phone_matrice.add(b02.getId());
        Phone_matrice.add(b10.getId());Phone_matrice.add(b11.getId());Phone_matrice.add(b12.getId());
        Phone_matrice.add(b20.getId());Phone_matrice.add(b21.getId());Phone_matrice.add(b22.getId());
        EnableAllButton(true);
        set_info(Player_1+" " + getString(R.string.Turn));
    }
    public void EnableAllButton(Boolean value){
        b00.setEnabled(value);b01.setEnabled(value);b02.setEnabled(value);
        b10.setEnabled(value);b11.setEnabled(value);b12.setEnabled(value);
        b20.setEnabled(value);b21.setEnabled(value);b22.setEnabled(value);
    }
    public void reset_status(){
        status[0][0]=b00.getId();status[0][1]=b01.getId();status[0][2]=b02.getId();
        status[1][0]=b10.getId();status[1][1]=b11.getId();status[1][2]=b12.getId();
        status[2][0]=b20.getId();status[2][1]=b21.getId();status[2][2]=b22.getId();


    }
    public void recive_intent(){
        Intent intent = getIntent();
        Player_1 =(intent.getStringExtra("Player_1"));
        Player_2 = (intent.getStringExtra("Player_2"));
    }

    public void play(View view){
        if(!Player_turn ) {
            random = new Random();
            int chose = random.nextInt(Phone_matrice.size());
            Button click = findViewById(Phone_matrice.get(chose));
            click.callOnClick();


        }
    }

}
