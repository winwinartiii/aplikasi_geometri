package com.example.bangunruang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private CardView btnKuis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        btnKuis = findViewById(R.id.btnKuis);

        btnKuis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefs prefs = new SharedPrefs(MainActivity.this);
                if (prefs.getKeyUserType().equals("murid")) {
                    Intent intent = new Intent(getApplicationContext(), MenuKuisActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getApplicationContext(), NilaiKuisActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void bangunDatar(View view) {
        Intent intent = new Intent(getApplicationContext(), BangunDatar.class);
        startActivity(intent);
    }

    public void bangunRuang(View view) {
        Intent intent = new Intent(getApplicationContext(), BangunRuang.class);
        startActivity(intent);
    }

    public void about(View view) {
        Intent intent = new Intent(getApplicationContext(), About.class);
        startActivity(intent);
    }

    public void logout(View view) {
        firebaseAuth.signOut();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }
}

