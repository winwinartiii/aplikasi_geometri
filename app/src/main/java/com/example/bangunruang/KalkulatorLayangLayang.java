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


public class KalkulatorLayangLayang extends AppCompatActivity {

    EditText editD1LayangLayang, editD2LayangLayang, editSisiPendekLayangLayang, editSisiPanjangLayangLayang;
    TextView textRumusKeliling;
    TextView textInputKeliling;
    TextView textHasilKeliling;
    TextView textRumusLuas;
    TextView textInputLuas;
    TextView textHasilLuas;

    Double D1, D2, sisiPendek, sisiPanjang, kllLayangLayang, luasLayangLayang;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalkulator_layanglayang);

        editD1LayangLayang = findViewById(R.id.editD1LayangLayang);
        editD2LayangLayang = findViewById(R.id.editD2LayangLayang);
        editSisiPendekLayangLayang = findViewById(R.id.editSisiPendekLayangLayang);
        editSisiPanjangLayangLayang = findViewById(R.id.editSisiPanjangLayangLayang);
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

    public void HitungKllLayangLayang(View view){
        sisiPanjang = Double.parseDouble(editSisiPanjangLayangLayang.getText().toString());
        sisiPendek = Double.parseDouble(editSisiPendekLayangLayang.getText().toString());
        kllLayangLayang = 2 * (sisiPendek + sisiPanjang);
        textInputKeliling.setText(String.format(Locale.getDefault(), "2 x (%1$s + %2$s)", sisiPendek, sisiPanjang));
        textHasilKeliling.setText(String.valueOf(kllLayangLayang));
    }

    public void HitungLuasLayangLayang(View view){
        D1 = Double.parseDouble(editD1LayangLayang.getText().toString());
        D2 = Double.parseDouble(editD2LayangLayang.getText().toString());
        luasLayangLayang = (D1 * D2) / 2;
        textInputLuas.setText(String.format(Locale.getDefault(), "(%1$s x %2$s) / 2", D1, D2));
        textHasilLuas.setText(String.valueOf(luasLayangLayang));
    }
}

