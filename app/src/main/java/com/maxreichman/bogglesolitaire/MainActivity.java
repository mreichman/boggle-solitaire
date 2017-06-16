package com.maxreichman.bogglesolitaire;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String[] vowels = new String[] {
            "A", "E", "I", "O", "U", "Y"
    };
    String[] consonants = new String[] {
            "B", "C", "D", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "V", "W", "X", "Z"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<String> randomLetterArray = new ArrayList<String>();
        for(int i = 0; i < 2; i=++) {
            int randomNumber = (int )(Math.random() * 5);
            randomLetterArray.add(vowels[randomNumber]);
        }
        for(int i = 0; i < 2; i=++) {
            int randomNumber = (int )(Math.random() * 19);
            randomLetterArray.add(consonants[randomNumber]);
        }

    }
}
