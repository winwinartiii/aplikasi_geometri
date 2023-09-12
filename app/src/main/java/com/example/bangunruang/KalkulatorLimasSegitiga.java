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



public class KalkulatorLimasSegitiga extends AppCompatActivity {

    EditText editTinggiLimasS3, editTSLimasS3, editTALimasS3, editSALimasS3;
    TextView textRumusLuas;
    TextView textInputLuas;
    TextView textHasilLuas;
    TextView textRumusVolume;
    TextView textInputVolume;
    TextView textHasilVolume;

    Double tinggiAlas, tinggiLimas, sisiAlas, tinggiSisi, volume, luas;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalkulator_limassegitiga);

        editTinggiLimasS3 = findViewById(R.id.editTinggiLimasS3);
        editTSLimasS3 = findViewById(R.id.editTSLimasS3);
        editTALimasS3 = findViewById(R.id.editTALimasS3);
        editSALimasS3 = findViewById(R.id.editSALimasS3);
        textRumusVolume = findViewById(R.id.text_rumus_volume);
        textInputVolume = findViewById(R.id.text_input_volume);
        textHasilVolume = findViewById(R.id.text_hasil_volume);
        textRumusLuas = findViewById(R.id.text_rumus_luas);
        textInputLuas = findViewById(R.id.text_input_luas);
        textHasilLuas = findViewById(R.id.text_hasil_luas);

        Intent intent = getIntent();

        final String namaBangunRuang = Objects.requireNonNull(intent.getExtras()).getString("namaBangunRuang");
        final String descBangunRuang = intent.getExtras().getString("descBangunRuang");
        final int whiteThumbBangunRuang = intent.getExtras().getInt("whiteThumbBangunRuang");
        final String luasBangunRuang = intent.getExtras().getString("luasBangunRuang");
        final String volumeBangunRuang = intent.getExtras().getString("volumeBangunRuang");
        final int rumusBangunRuang = intent.getExtras().getInt("rumusBangunRuang");

        FloatingActionButton fab = findViewById(R.id.fabBack);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MateriBangunRuang.class);

                intent.putExtra("namaBangunRuang", namaBangunRuang);
                intent.putExtra("descBangunRuang", descBangunRuang);
                intent.putExtra("whiteThumbBangunRuang", whiteThumbBangunRuang);
                intent.putExtra("luasBangunRuang", luasBangunRuang);
                intent.putExtra("volumeBangunRuang", volumeBangunRuang);
                intent.putExtra("rumusBangunRuang", rumusBangunRuang);

                startActivity(intent);
            }
        });

        textRumusLuas.setText(luasBangunRuang);
        textRumusVolume.setText(volumeBangunRuang);
    }

    public void HitungVolumeLimasSegitiga(View view){
        tinggiAlas = Double.parseDouble(editTALimasS3.getText().toString());
        sisiAlas = Double.parseDouble(editSALimasS3.getText().toString());
        tinggiLimas = Double.parseDouble(editTinggiLimasS3.getText().toString());
        volume = (tinggiAlas*sisiAlas*tinggiLimas)/6;
        textInputVolume.setText(String.format(Locale.getDefault(), "%1$s x (%2$s x %3$s) / 2) / 3", tinggiLimas, sisiAlas, tinggiAlas));
        textHasilVolume.setText(String.valueOf(volume));
    }
    public void HitungLuasLimasSegitiga(View view){
        tinggiAlas = Double.parseDouble(editTALimasS3.getText().toString());
        sisiAlas = Double.parseDouble(editSALimasS3.getText().toString());
        tinggiSisi = Double.parseDouble(editTSLimasS3.getText().toString());
        luas = (tinggiAlas*sisiAlas)/2 + 3*(sisiAlas*tinggiSisi)/2;
        textInputLuas.setText(String.format(Locale.getDefault(), "(%1$s x %2$s) / 2 + 3 x (%1$s x %3$s) / 2", sisiAlas, tinggiAlas, tinggiSisi));
        textHasilLuas.setText(String.valueOf(luas));
    }
}

