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


public class KalkulatorKerucut extends AppCompatActivity {

    EditText editJariKerucut, editJSKerucut, editTinggiKerucut;
    TextView textRumusLuas;
    TextView textInputLuas;
    TextView textHasilLuas;
    TextView textRumusVolume;
    TextView textInputVolume;
    TextView textHasilVolume;

    Double jari, jariSisi, tinggi, volume, luas;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalkulator_kerucut);

        editJariKerucut = findViewById(R.id.editJariKerucut);
        editJSKerucut = findViewById(R.id.editJSKerucut);
        editTinggiKerucut = findViewById(R.id.editTinggiKerucut);
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

    public void HitungVolumeKerucut(View view){
        jari = Double.parseDouble(editJariKerucut.getText().toString());
        tinggi = Double.parseDouble(editTinggiKerucut.getText().toString());
        volume = (3.14*jari*jari*tinggi)/3;
        textInputVolume.setText(String.format(Locale.getDefault(), "(3.14 x %1$s x %1$s x %2$s) / 3", jari, tinggi));
        textHasilVolume.setText(String.valueOf(volume));
    }
    public void HitungLuasKerucut(View view){
        jari = Double.parseDouble(editJariKerucut.getText().toString());
        jariSisi = Double.parseDouble(editJSKerucut.getText().toString());
        luas = (3.14*jari*jari)+(3.14*jari*jariSisi);
        textInputLuas.setText(String.format(Locale.getDefault(), "(3.14 x %1$s x %1$s) + (3.14 x %1$s x %2$s)", jari, jariSisi));
        textHasilLuas.setText(String.valueOf(luas));
    }
}

