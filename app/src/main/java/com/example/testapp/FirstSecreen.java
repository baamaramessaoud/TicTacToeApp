package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class FirstSecreen extends AppCompatActivity {
    EditText First_player;
    EditText Second_Player;
    Button Start;
    TextView info;
    RadioButton play_with_friend;
    RadioButton play_with_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_secreen);
        play_with_friend = findViewById(R.id.play_with_friend);
        play_with_phone = findViewById(R.id.play_with_phone);
        play_with_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Second_Player.setText(getString(R.string.phone));
                Second_Player.setEnabled(false);

            }
        });
        play_with_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Second_Player.setEnabled(true);
            }
        });

        info = findViewById(R.id.info);
        First_player = findViewById(R.id.First_Player);
        Second_Player = findViewById(R.id.Second_Player);
        Start = findViewById(R.id.Start_Button);
        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(First_player.getText().toString().equals(getString(R.string.name)) || First_player.getText().toString().equals("") || Second_Player.getText().toString().equals(getString(R.string.name)) || Second_Player.getText().toString().equals("")){
                    info.setText(getString(R.string.info));
                }
                else {
                    sendIntent();
                }
            }
        });

    }

    public void sendIntent(){
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("Player_1",First_player.getText().toString());
        intent.putExtra("Player_2",Second_Player.getText().toString());
        startActivity(intent);

    }
}
