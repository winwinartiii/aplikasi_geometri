package com.example.bangunruang;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Locale;
import java.util.Objects;



public class KalkulatorTrapesium extends AppCompatActivity {
    EditText editATrapesium;
    EditText editBTrapesium;
    EditText editTinggiTrapesium;
    EditText editS1Trapesium;
    EditText editS2Trapesium;
    TextView textRumusKeliling;
    TextView textInputKeliling;
    TextView textHasilKeliling;
    TextView textRumusLuas;
    TextView textInputLuas;
    TextView textHasilLuas;

    Double sisi1;
    Double sisi2;
    Double tinggi;
    Double sisiA;
    Double sisiB;
    Double kllTrapesium;
    Double luasTrapesium;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalkulator_trapesium);

        editATrapesium = findViewById(R.id.editATrapesium);
        editBTrapesium = findViewById(R.id.editBTrapesium);
        editTinggiTrapesium = findViewById(R.id.editTinggiTrapesium);
        editS1Trapesium = findViewById(R.id.editS1Trapesium);
        editS2Trapesium = findViewById(R.id.editS2Trapesium);
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

    public void HitungKllTrapesium(View view){
        sisi1 = Double.parseDouble(editS1Trapesium.getText().toString());
        sisi2 = Double.parseDouble(editS2Trapesium.getText().toString());
        sisiA = Double.parseDouble(editATrapesium.getText().toString());
        sisiB = Double.parseDouble(editBTrapesium.getText().toString());
        kllTrapesium = sisi1 + sisi2 + sisiA + sisiB;
        textInputKeliling.setText(String.format(Locale.getDefault(), "%1$s + %2$s + %3$s + %4$s", sisi1, sisi2, sisiA, sisiB));
        textHasilKeliling.setText(String.valueOf(kllTrapesium));
    }

    public void HitungLuasTrapesium(View view){
        sisiA = Double.parseDouble(editATrapesium.getText().toString());
        sisiB = Double.parseDouble(editBTrapesium.getText().toString());
        tinggi = Double.parseDouble(editTinggiTrapesium.getText().toString());
        luasTrapesium = ((sisiA + sisiB)*tinggi)/2;
        textInputLuas.setText(String.format(Locale.getDefault(), "{(%1$s + %2$s) x %3$s} / 2", sisiA, sisiB, tinggi));
        textHasilLuas.setText(String.valueOf(luasTrapesium));
    }
}
