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



public class KalkulatorPrisma extends AppCompatActivity {

    EditText editPanjangPrisma, editLebarPrisma, editTinggiPrisma;
    TextView textRumusLuas;
    TextView textInputLuas;
    TextView textHasilLuas;
    TextView textRumusVolume;
    TextView textInputVolume;
    TextView textHasilVolume;

    Double panjang,tinggi,lebar, volume, luas;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalkulator_prisma);

        editPanjangPrisma = findViewById(R.id.editPanjangPrisma);
        editLebarPrisma = findViewById(R.id.editLebarPrisma);
        editTinggiPrisma = findViewById(R.id.editTinggiPrisma);
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

    public void HitungVolumePrisma(View view){
        panjang = Double.parseDouble(editPanjangPrisma.getText().toString());
        lebar = Double.parseDouble(editLebarPrisma.getText().toString());
        tinggi = Double.parseDouble(editTinggiPrisma.getText().toString());
        volume = (panjang*lebar*tinggi)/2;
        textInputVolume.setText(String.format(Locale.getDefault(), "(%1$s x %2$s x %3$s) / 2", panjang, lebar, tinggi));
        textHasilVolume.setText(String.valueOf(volume));
    }
    public void HitungLuasPrisma(View view){
        panjang = Double.parseDouble(editPanjangPrisma.getText().toString());
        lebar = Double.parseDouble(editLebarPrisma.getText().toString());
        tinggi = Double.parseDouble(editTinggiPrisma.getText().toString());
        luas = (3*panjang*lebar)+(lebar*tinggi);
        textInputLuas.setText(String.format(Locale.getDefault(), "(3 x %1$s x %2$s) + (%2$s x %3$s)", panjang, lebar, tinggi));
        textHasilLuas.setText(String.valueOf(luas));
    }
}

