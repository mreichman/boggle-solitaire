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
    @Bind(R.id.score) TextView mScore;
    Integer mCurrentScore = 0;

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
                intent.putExtra("score", mCurrentScore.toString());
                startActivity(intent);
            }
        }.start();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mScore.setText(mCurrentScore.toString());
        final ArrayList<String> randomLetterArray = new ArrayList<String>();
        for(int i = 0; i < 5; i++) {
            int randomNumber = (int )(Math.random() * 5);
            randomLetterArray.add(vowels[randomNumber]);
        }
        for(int i = 0; i < 7; i++) {
            int randomNumber = (int )(Math.random() * 19);
            randomLetterArray.add(consonants[randomNumber]);
        }
        shuffle(randomLetterArray);
        StringBuilder builder = new StringBuilder();
        for(String s : randomLetterArray) {
            builder.append(s);
        }
        builder.insert(4, "\n");
        builder.insert(9, "\n");
        String randomLetterString = builder.toString();
        mRandomLetters.setText(randomLetterString);
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                final ArrayList<String> randomLetterArray2 = new ArrayList<>();
                for(int i = 0; i < 12; i++) {
                    randomLetterArray2.add(randomLetterArray.get(i));
                }
                int duration = Toast.LENGTH_SHORT;
                String enteredWord = mEditText.getText().toString().toUpperCase();
                int enteredWordLength = enteredWord.length();
                String[] enteredWordArray;
                enteredWordArray = enteredWord.split("");
                Integer counter = 0;
                String isValidWord = "valid";
                for(String enteredLetter : enteredWordArray) {
                    for(String randomLetter : randomLetterArray2) {
                        if (enteredLetter.equals(randomLetter)) {
                            counter++;
                            randomLetterArray2.remove(randomLetter);
                            break;
                        }
                    }
                }
                if (counter != enteredWordLength) {
                    isValidWord = "notValid";
                }
                if (results.contains(enteredWord)) {
                    isValidWord = "alreadyEntered";
                }
                if (isValidWord == "valid") {
                    results.add(enteredWord);
                    CharSequence text = "Great Job!";
                    Toast great = Toast.makeText(context, text, duration);
                    mCurrentScore++;
                    mScore.setText(mCurrentScore.toString());
                    great.show();
                } else if (isValidWord == "alreadyEntered") {
                    CharSequence text = "You already entered that word";
                    Toast wordAlreadyEntered = Toast.makeText(context, text, duration);
                    wordAlreadyEntered.show();
                } else {
                    CharSequence text = "Sorry, the word you entered is not valid";
                    Toast sorry = Toast.makeText(context, text, duration);
                    sorry.show();
                }
                for(int i = 0; i < 12; i++) {
                    randomLetterArray2.add(randomLetterArray.get(i));
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
                builder2.insert(4, "\n");
                builder2.insert(9, "\n");
                String randomLetterString = builder2.toString();
                mRandomLetters.setText(randomLetterString);
            }
        });
    }
}
