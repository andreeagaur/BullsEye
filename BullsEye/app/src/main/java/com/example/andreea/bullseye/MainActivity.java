package com.example.andreea.bullseye;

import android.icu.text.SymbolTable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Button reset_score;
    Button bulls_eye;
    TextView score_counter;
    TextView random_number;
    TextView correct_hit;
    SeekBar seekBar;
    int score_count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reset_score = findViewById(R.id.reset_score);
        bulls_eye = findViewById(R.id.bulls_eye);
        score_counter = findViewById(R.id.score_counter);
        random_number = findViewById(R.id.random_number);
        correct_hit = findViewById(R.id.correct_hit);
        seekBar = findViewById(R.id.seekBar);

        final MediaPlayer slider_movement = MediaPlayer.create(this, R.raw.movement_sound);

        random_number.setText(String.valueOf(random_number_generator()));
        score_count = 0;
        reset_score.setOnClickListener(this);
        bulls_eye.setOnClickListener(this);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangeValue = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progressChangeValue = i;
                //slider_movement.start();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                slider_movement.start();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.reset_score:
                score_counter.setText("000");
                break;

            case R.id.bulls_eye:

                String sequence = random_number.getText().toString();
                int number = Integer.parseInt(sequence);

                int user_progress = seekBar.getProgress();

                if (user_progress == number){
                    score_count += number;
                    System.out.println("one Banana");
                    score_counter.setText(String.valueOf(score_count));
                    System.out.println("2 Banana");
                    correct_hit.setVisibility(View.VISIBLE);
                    System.out.println("3 Banana");
                    random_number.setText(String.valueOf(random_number_generator()));
                    System.out.println("4 Banana");
                }

                if (user_progress > number){
                    score_count += (user_progress - number);
                    System.out.println("one Banana");
                    score_counter.setText(String.valueOf(score_count));
                    System.out.println("two Banana");
                    correct_hit.setText("Your value: "+ String.valueOf(user_progress));
                    correct_hit.setVisibility(View.VISIBLE);
                    System.out.println("three Banana");
                    random_number.setText(String.valueOf(random_number_generator()));
                    System.out.println("four Banana");
                }

                if(user_progress < number){
                    score_count += (number - user_progress);
                    System.out.println("one Banana");
                    score_counter.setText(String.valueOf(score_count));
                    System.out.println("2 Banana");
                    correct_hit.setText("Your value: " + String.valueOf(user_progress));
                    correct_hit.setVisibility(View.VISIBLE);
                    System.out.println("3 Banana");
                    random_number.setText(String.valueOf(random_number_generator()));
                    System.out.println("4 Banana");
                }
        }
    }


    int random_number_generator(){
        int min = 0;
        int max = 100;
        Random r = new Random();
        int value = r.nextInt(max-min+1)+min;
        return value;
    }

}
