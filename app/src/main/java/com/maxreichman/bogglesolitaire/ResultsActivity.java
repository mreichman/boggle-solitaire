package com.maxreichman.bogglesolitaire;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ResultsActivity extends AppCompatActivity {
    @Bind(R.id.results) TextView mResults;
    @Bind(R.id.finalScore) TextView mFinalScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String results = intent.getStringExtra("results");
        String score = intent.getStringExtra("score");
        mResults.setText(results);
        mFinalScore.setText(score);
    }
}
