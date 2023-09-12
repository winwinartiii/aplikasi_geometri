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


public class KalkulatorBola extends AppCompatActivity {

    EditText editJariBola;
    TextView textRumusLuas;
    TextView textInputLuas;
    TextView textHasilLuas;
    TextView textRumusVolume;
    TextView textInputVolume;
    TextView textHasilVolume;

    Double jariBola, volume, luas;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalkulator_bola);

        editJariBola = findViewById(R.id.editJariBola);
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

    public void HitungVolumeBola(View view){
        jariBola = Double.parseDouble(editJariBola.getText().toString());
        volume = 4*(3.14*jariBola*jariBola*jariBola)/3;
        textInputVolume.setText(String.format(Locale.getDefault(), "(4 x 3.14 x %1$s x %1$s x %1$s) / 3", jariBola));
        textHasilVolume.setText(String.valueOf(volume));
    }

    public void HitungLuasBola(View view){
        jariBola = Double.parseDouble(editJariBola.getText().toString());
        luas = 4*3.14*jariBola*jariBola;
        textInputLuas.setText(String.format(Locale.getDefault(), "4 x 3.14 x %1$s x %1$s", jariBola));
        textHasilLuas.setText(String.valueOf(luas));
    }
}

