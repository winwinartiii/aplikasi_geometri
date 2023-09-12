package com.example.bangunruang;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;


public class MateriBangunRuang extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi_bangun_ruang);

        Intent intent = getIntent();
        final String namaBangunRuang = Objects.requireNonNull(intent.getExtras()).getString("namaBangunRuang");
        final String descBangunRuang = intent.getExtras().getString("descBangunRuang");
        final int whiteThumbBangunRuang = intent.getExtras().getInt("whiteThumbBangunRuang");
        final String luasBangunRuang = intent.getExtras().getString("luasBangunRuang");
        final String volumeBangunRuang = intent.getExtras().getString("volumeBangunRuang");
        final int rumusBangunRuang = intent.getExtras().getInt("rumusBangunRuang");

        TextView namaMateriBangunRuang = findViewById(R.id.namaMateriBangunRuang);
        ImageView thumbMateriBangunRuang = findViewById(R.id.imageMateriBangunRuang);
        TextView descMateriBangunRuang = findViewById(R.id.descBangunRuang);
        TextView materiLuasBangunRuang = findViewById(R.id.textLuasBangunRuang);
        TextView materiVolumeBangunRuang = findViewById(R.id.textVolumeBangunRuang);
        ImageView materiImageRumusBangunRuang = findViewById(R.id.imageRumusBangunRuang);

        namaMateriBangunRuang.setText(namaBangunRuang);
        thumbMateriBangunRuang.setImageResource(whiteThumbBangunRuang);
        descMateriBangunRuang.setText(descBangunRuang);
        materiVolumeBangunRuang.setText(volumeBangunRuang);
        materiLuasBangunRuang.setText(luasBangunRuang);
        materiImageRumusBangunRuang.setImageResource(rumusBangunRuang);

        FloatingActionButton fabback = findViewById(R.id.fabBack);
        fabback.setOnClickListener(view -> {
            Intent intent1 = new Intent(getApplicationContext(), BangunRuang.class);
            startActivity(intent1);
        });
        FloatingActionButton fabcalc = findViewById(R.id.fabKalkulator);
        fabcalc.setOnClickListener(view -> {
            Intent intent12 = new Intent(getApplicationContext(), KalkulatorBangunRuang.class);

            intent12.putExtra("namaBangunRuang", namaBangunRuang);
            intent12.putExtra("descBangunRuang", descBangunRuang);
            intent12.putExtra("whiteThumbBangunRuang", whiteThumbBangunRuang);
            intent12.putExtra("luasBangunRuang", luasBangunRuang);
            intent12.putExtra("volumeBangunRuang", volumeBangunRuang);
            intent12.putExtra("rumusBangunRuang", rumusBangunRuang);

            startActivity(intent12);
        });
    }
}


