package com.example.bangunruang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MenuKuisActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etClass;
    private Button btnStart;
    private FloatingActionButton fabBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_kuis);

        etName = findViewById(R.id.etName);
        etClass = findViewById(R.id.etClass);
        btnStart = findViewById(R.id.btnStart);
        fabBack = findViewById(R.id.fabBack);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = etName.getText().toString();
                if (nama.isEmpty()) {
                    etName.setError("Nama tidak boleh kosong");
                    return;
                }

                String kelas = etClass.getText().toString();
                if (kelas.isEmpty()) {
                    etClass.setError("Kelas tidak boleh kosong");
                    return;
                }

                Intent intent = new Intent(MenuKuisActivity.this, KuisActivity.class);
                intent.putExtra("nama", nama);
                intent.putExtra("kelas", kelas);
                startActivity(intent);
            }
        });

        fabBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}