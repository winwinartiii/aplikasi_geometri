package com.example.bangunruang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HasilKuisActivity extends AppCompatActivity {

    private TextView tvScore;
    private FloatingActionButton fabBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_kuis);

        tvScore = findViewById(R.id.tvScore);
        fabBack = findViewById(R.id.fabBack);

        int score = getIntent().getIntExtra("nilai", 0);
        tvScore.setText(String.valueOf(score));

        fabBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}