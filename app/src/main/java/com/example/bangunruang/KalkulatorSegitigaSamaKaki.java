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



public class KalkulatorSegitigaSamaKaki extends AppCompatActivity {
    EditText editAlasSegitigaSamaKaki;
    EditText editTinggiSegitigaSamaKaki;
    EditText editSisiSegitigaSamaKaki;
    TextView textRumusKeliling;
    TextView textInputKeliling;
    TextView textHasilKeliling;
    TextView textRumusLuas;
    TextView textInputLuas;
    TextView textHasilLuas;

    Double alas;
    Double sisi;
    Double tinggi;
    Double kllSegitigaSamaKaki;
    Double luasSegitigaSamaKaki;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalkulator_segitiga_samakaki);

        editAlasSegitigaSamaKaki = findViewById(R.id.editAlasSegitigaSamaKaki);
        editTinggiSegitigaSamaKaki = findViewById(R.id.editTinggiSegitigaSamaKaki);
        editSisiSegitigaSamaKaki = findViewById(R.id.editSisiSegitigaSamaKaki);
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

    public void HitungKllSegitigaSamaKaki(View view){
        alas = Double.parseDouble(editAlasSegitigaSamaKaki.getText().toString());
        sisi = Double.parseDouble(editSisiSegitigaSamaKaki.getText().toString());
        kllSegitigaSamaKaki = 2 * sisi + alas;
        textInputKeliling.setText(String.format(Locale.getDefault(), "2 x %1$s + %2$s", sisi, alas));
        textHasilKeliling.setText(String.valueOf(kllSegitigaSamaKaki));
    }

    public void HitungLuasSegitigaSamaKaki(View view){
        alas = Double.parseDouble(editAlasSegitigaSamaKaki.getText().toString());
        tinggi = Double.parseDouble(editTinggiSegitigaSamaKaki.getText().toString());
        luasSegitigaSamaKaki = alas * tinggi / 2;
        textInputLuas.setText(String.format(Locale.getDefault(), "(%1$s x %2$s) / 2", alas, tinggi));
        textHasilLuas.setText(String.valueOf(luasSegitigaSamaKaki));
    }
}
