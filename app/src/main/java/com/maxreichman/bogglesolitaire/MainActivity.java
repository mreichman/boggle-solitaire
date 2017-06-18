package com.maxreichman.bogglesolitaire;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.StringBuilder;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

import static java.util.Collections.shuffle;

public class MainActivity extends AppCompatActivity {
    String[] vowels = new String[] {
            "A", "E", "I", "O", "U"
    };
    String[] consonants = new String[] {
            "B", "C", "D", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "V", "W", "X", "Y", "Z"
    };

    @Bind(R.id.randomLettersTextView) TextView mRandomLetters;
    @Bind(R.id.editText) EditText mEditText;
    @Bind(R.id.button) Button mSubmit;
    @Bind(R.id.timer) TextView mTimer;
    @Bind(R.id.scramble) Button mScramble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final ArrayList<String> results = new ArrayList();
        new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                mTimer.setText("seconds remaining: " + millisUntilFinished / 1000);
            }
            public void onFinish() {
                Intent intent = new Intent(MainActivity.this, ResultsActivity.class);
                intent.putExtra("results", results.toString());
                startActivity(intent);
            }
        }.start();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        final ArrayList<String> randomLetterArray = new ArrayList<String>();
        for(int i = 0; i < 2; i++) {
            int randomNumber = (int )(Math.random() * 5);
            randomLetterArray.add(vowels[randomNumber]);
        }
        for(int i = 0; i < 6; i++) {
            int randomNumber = (int )(Math.random() * 19);
            randomLetterArray.add(consonants[randomNumber]);
        }
        final ArrayList<String> randomLetterArray2 = randomLetterArray;
        shuffle(randomLetterArray);
        StringBuilder builder = new StringBuilder();
        for(String s : randomLetterArray) {
            builder.append(s);
        }
        String randomLetterString = builder.toString();
        mRandomLetters.setText(randomLetterString);
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                String enteredWord = mEditText.getText().toString().toUpperCase();
                String[] enteredWordArray;
                enteredWordArray = enteredWord.split("");
                Integer counter = 8;
                for(String enteredLetter : enteredWordArray) {
                    for(String randomLetter : randomLetterArray2) {
                        if (enteredLetter.equals(randomLetter)) {
                            counter--;
                        }
                    }
                }
                if (counter < 6) {
                    results.add(enteredWord);
                    CharSequence text = "Great Job!";
                    Toast great = Toast.makeText(context, text, duration);
                    great.show();
                } else {
                    CharSequence text = "Sorry, the word you entered is not valid";
                    Toast sorry = Toast.makeText(context, text, duration);
                    sorry.show();
                }
            }
        });

        mScramble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shuffle(randomLetterArray);
                StringBuilder builder2 = new StringBuilder();
                for(String s : randomLetterArray) {
                    builder2.append(s);
                }
                String randomLetterString = builder2.toString();
                mRandomLetters.setText(randomLetterString);
            }
        });
    }
}
