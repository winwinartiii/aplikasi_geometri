package com.example.bangunruang;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;
import java.util.Objects;


public class KalkulatorSegitigaSikuSiku extends AppCompatActivity {
    EditText editAlasSegitigaSikuSiku;
    EditText editTinggiSegitigaSikuSiku;
    EditText editSisiSegitigaSikuSiku;
    TextView textRumusKeliling;
    TextView textInputKeliling;
    TextView textHasilKeliling;
    TextView textRumusLuas;
    TextView textInputLuas;
    TextView textHasilLuas;

    Double alas;
    Double sisi;
    Double tinggi;
    Double kllSegitigaSikuSiku;
    Double luasSegitigaSikuSiku;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalkulator_segitiga_sikusiku);

        editAlasSegitigaSikuSiku = findViewById(R.id.editAlasSegitigaSikuSiku);
        editTinggiSegitigaSikuSiku = findViewById(R.id.editTinggiSegitigaSikuSiku);
        editSisiSegitigaSikuSiku = findViewById(R.id.editSisiSegitigaSikuSiku);
        textRumusLuas = findViewById(R.id.text_rumus_luas);
        textInputLuas = findViewById(R.id.text_input_luas);
        textHasilLuas = findViewById(R.id.text_hasil_luas);
        textRumusKeliling = findViewById(R.id.text_rumus_keliling);
        textInputKeliling = findViewById(R.id.text_input_keliling);
        textHasilKeliling = findViewById(R.id.text_hasil_keliling);

        Intent intent = getIntent();
        final String namaBangunDatar = Objects.requireNonNull(intent.getExtras()).getString("namaBangunDatar");
        final String descBangunDatar = intent.getExtras().getString("descBangunDatar");
        final int whiteThumbBangunDatar = intent.getExtras().getInt("whiteThumbBangunDatar");
        final String luasBangunDatar = intent.getExtras().getString("luasBangunDatar");
        final String kelilingBangunDatar = intent.getExtras().getString("kelilingBangunDatar");
        final int imageRumusBangunDatar = intent.getExtras().getInt("rumusBangunDatar");

        FloatingActionButton fab = findViewById(R.id.fabBack);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MateriBangunDatar.class);
                intent.putExtra("namaBangunDatar", namaBangunDatar);
                intent.putExtra("whiteThumbBangunDatar", whiteThumbBangunDatar);
                intent.putExtra("descBangunDatar", descBangunDatar);
                intent.putExtra("kelilingBangunDatar", kelilingBangunDatar);
                intent.putExtra("luasBangunDatar", luasBangunDatar);
                intent.putExtra("rumusBangunDatar", imageRumusBangunDatar);

                startActivity(intent);
            }
        });

        textRumusLuas.setText(luasBangunDatar);
        textRumusKeliling.setText(kelilingBangunDatar);
    }

    public void HitungKllSegitigaSikuSiku(View view){
        alas = Double.parseDouble(editAlasSegitigaSikuSiku.getText().toString());
        sisi = Double.parseDouble(editSisiSegitigaSikuSiku.getText().toString());
        tinggi = Double.parseDouble(editTinggiSegitigaSikuSiku.getText().toString());
        kllSegitigaSikuSiku = tinggi + sisi + alas;
        textInputKeliling.setText(String.format(Locale.getDefault(), "%1$s + %2$s + %3$s", tinggi, sisi, alas));
        textHasilKeliling.setText(String.valueOf(kllSegitigaSikuSiku));
    }

    public void HitungLuasSegitigaSikuSiku(View view){
        alas = Double.parseDouble(editAlasSegitigaSikuSiku.getText().toString());
        tinggi = Double.parseDouble(editTinggiSegitigaSikuSiku.getText().toString());
        luasSegitigaSikuSiku = alas * tinggi / 2;
        textInputLuas.setText(String.format(Locale.getDefault(), "(%1$s x %2$s) / 2", alas, tinggi));
        textHasilLuas.setText(String.valueOf(luasSegitigaSikuSiku));
    }
}
