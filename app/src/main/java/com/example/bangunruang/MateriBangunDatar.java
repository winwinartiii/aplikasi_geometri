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


public class MateriBangunDatar extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi_bangun_datar);

        Intent intent = getIntent();
        final String namaBangunDatar = Objects.requireNonNull(intent.getExtras()).getString("namaBangunDatar");
        final String descBangunDatar = intent.getExtras().getString("descBangunDatar");
        final int whiteThumbBangunDatar = intent.getExtras().getInt("whiteThumbBangunDatar");
        final String luasBangunDatar = intent.getExtras().getString("luasBangunDatar");
        final String kelilingBangunDatar = intent.getExtras().getString("kelilingBangunDatar");
        final int imageRumusBangunDatar = intent.getExtras().getInt("rumusBangunDatar");

        TextView namaMateriBangunDatar = findViewById(R.id.namaMateriBangunDatar);
        ImageView thumbMateriBangunDatar = findViewById(R.id.imageMateriBangunDatar);
        TextView descMateriBangunDatar = findViewById(R.id.descBangunDatar);
        TextView materiLuasBangunDatar = findViewById(R.id.textLuasBangunDatar);
        TextView materiKelilingBangunDatar = findViewById(R.id.textKelilingBangunDatar);
        ImageView materiRumusBangunDatar = findViewById(R.id.imageRumusBangunDatar);

        namaMateriBangunDatar.setText(namaBangunDatar);
        thumbMateriBangunDatar.setImageResource(whiteThumbBangunDatar);
        descMateriBangunDatar.setText(descBangunDatar);
        materiKelilingBangunDatar.setText(kelilingBangunDatar);
        materiLuasBangunDatar.setText(luasBangunDatar);
        materiRumusBangunDatar.setImageResource(imageRumusBangunDatar);

        FloatingActionButton fabback = findViewById(R.id.fabBack);
        fabback.setOnClickListener(view -> {
            Intent intent2 = new Intent(getApplicationContext(), BangunDatar.class);
            startActivity(intent2);
        });
        FloatingActionButton fabcalc = findViewById(R.id.fabKalkulator);
        fabcalc.setOnClickListener(view -> {
            Intent intent1 = new Intent(getApplicationContext(), KalkulatorBangunDatar.class);
            intent1.putExtra("namaBangunDatar", namaBangunDatar);
            intent1.putExtra("whiteThumbBangunDatar", whiteThumbBangunDatar);
            intent1.putExtra("descBangunDatar", descBangunDatar);
            intent1.putExtra("kelilingBangunDatar", kelilingBangunDatar);
            intent1.putExtra("luasBangunDatar", luasBangunDatar);
            intent1.putExtra("rumusBangunDatar", imageRumusBangunDatar);

            startActivity(intent1);

        });
    }
}
