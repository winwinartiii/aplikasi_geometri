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


public class KalkulatorPersegi extends AppCompatActivity {

    EditText editSisiPersegi;
    TextView textRumusKeliling;
    TextView textInputKeliling;
    TextView textHasilKeliling;
    TextView textRumusLuas;
    TextView textInputLuas;
    TextView textHasilLuas;

    Double sisi;
    Double kllPersegi;
    Double luasPersegi;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalkulator_persegi);

        editSisiPersegi = findViewById(R.id.editsisipersegi);
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
        fab.setOnClickListener(view -> {
            Intent intent1 = new Intent(getApplicationContext(), MateriBangunDatar.class);
            intent1.putExtra("namaBangunDatar", namaBangunDatar);
            intent1.putExtra("whiteThumbBangunDatar", whiteThumbBangunDatar);
            intent1.putExtra("descBangunDatar", descBangunDatar);
            intent1.putExtra("kelilingBangunDatar", kelilingBangunDatar);
            intent1.putExtra("luasBangunDatar", luasBangunDatar);
            intent1.putExtra("rumusBangunDatar", imageRumusBangunDatar);

            startActivity(intent1);
        });

        textRumusLuas.setText(luasBangunDatar);
        textRumusKeliling.setText(kelilingBangunDatar);
    }

    public void HitungKllPersegi(View view){
        sisi = Double.parseDouble(editSisiPersegi.getText().toString());
        kllPersegi = sisi * 4;
        textInputKeliling.setText(String.format(Locale.getDefault(), "4 x %1$s", sisi));
        textHasilKeliling.setText(String.valueOf(kllPersegi));
    }
    public void HitungLuasPersegi(View view){
        sisi = Double.parseDouble(editSisiPersegi.getText().toString());
        luasPersegi = sisi * sisi;
        textInputLuas.setText(String.format(Locale.getDefault(), "%1$s x %1$s", sisi));
        textHasilLuas.setText(String.valueOf(luasPersegi));
    }


}

