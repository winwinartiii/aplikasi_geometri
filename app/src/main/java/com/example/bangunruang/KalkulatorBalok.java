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


public class KalkulatorBalok extends AppCompatActivity {

    EditText editPanjangBalok;
    EditText editLebarBalok;
    EditText editTinggiBalok;
    TextView textRumusLuas;
    TextView textInputLuas;
    TextView textHasilLuas;
    TextView textRumusVolume;
    TextView textInputVolume;
    TextView textHasilVolume;

    Double panjang, lebar, tinggi, volumeBalok, luasBalok;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalkulator_balok);

        editPanjangBalok = findViewById(R.id.editPanjangBalok);
        editLebarBalok = findViewById(R.id.editLebarBalok);
        editTinggiBalok = findViewById(R.id.editTinggiBalok);
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
        fab.setOnClickListener(view -> {
            Intent intent1 = new Intent(getApplicationContext(), MateriBangunRuang.class);

            intent1.putExtra("namaBangunRuang", namaBangunRuang);
            intent1.putExtra("descBangunRuang", descBangunRuang);
            intent1.putExtra("whiteThumbBangunRuang", whiteThumbBangunRuang);
            intent1.putExtra("luasBangunRuang", luasBangunRuang);
            intent1.putExtra("volumeBangunRuang", volumeBangunRuang);
            intent1.putExtra("rumusBangunRuang", rumusBangunRuang);

            startActivity(intent1);
        });

        textRumusLuas.setText(luasBangunRuang);
        textRumusVolume.setText(volumeBangunRuang);
    }

    public void HitungVolumeBalok(View view){
        panjang = Double.parseDouble(editPanjangBalok.getText().toString());
        lebar = Double.parseDouble(editLebarBalok.getText().toString());
        tinggi = Double.parseDouble(editTinggiBalok.getText().toString());
        volumeBalok = panjang * lebar * tinggi;
        textInputVolume.setText(String.format(Locale.getDefault(), "%1$s x %2$s x %3$s", panjang, lebar, tinggi));
        textHasilVolume.setText(String.valueOf(volumeBalok));

    }
    public void HitungLuasBalok(View view){
        panjang = Double.parseDouble(editPanjangBalok.getText().toString());
        lebar = Double.parseDouble(editLebarBalok.getText().toString());
        tinggi = Double.parseDouble(editTinggiBalok.getText().toString());
        luasBalok = 2*(panjang*lebar)+2*(panjang*tinggi)+2*(tinggi*lebar);
        textInputLuas.setText(String.format(Locale.getDefault(), "2 x (%1$s x %2$s) + 2 x (%1$s x %3$s) + 2 x (%3$s x %2$s)", panjang, lebar, tinggi));
        textHasilLuas.setText(String.valueOf(luasBalok));

    }


}

