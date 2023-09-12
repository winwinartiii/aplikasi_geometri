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


public class KalkulatorKubus extends AppCompatActivity {

    EditText editSisiKubus;
    TextView textRumusLuas;
    TextView textInputLuas;
    TextView textHasilLuas;
    TextView textRumusVolume;
    TextView textInputVolume;
    TextView textHasilVolume;

    Double sisi;
    Double volumeKubus;
    Double luasKubus;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalkulator_kubus);

        editSisiKubus = findViewById(R.id.editsisikubus);
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

    public void HitungVolumeKubus(View view){
        sisi = Double.parseDouble(editSisiKubus.getText().toString());
        volumeKubus = sisi * sisi * sisi;
        textInputVolume.setText(String.format(Locale.getDefault(), "%1$s x %1$s x %1$s", sisi));
        textHasilVolume.setText(String.valueOf(volumeKubus));
    }
    public void HitungLuasKubus(View view){
        sisi = Double.parseDouble(editSisiKubus.getText().toString());
        luasKubus = 6 * sisi * sisi;
        textInputLuas.setText(String.format(Locale.getDefault(), "6 x %1$s x %1$s", sisi));
        textHasilLuas.setText(String.valueOf(luasKubus));

    }


}

