package com.example.bangunruang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KuisActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    private RecyclerView rvKuis;
    private FloatingActionButton fabBack;
    private FloatingActionButton fabFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuis);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        rvKuis = findViewById(R.id.rvKuis);
        fabBack = findViewById(R.id.fabBack);
        fabFinish = findViewById(R.id.fabFinish);

        String nama = getIntent().getStringExtra("nama");
        String kelas = getIntent().getStringExtra("kelas");

        List<KuisItem> kuisList = new ArrayList<>();

        kuisList.add(new KuisItem(
                "Sebuah balok memiliki panjang 28 cm, lebar 14 cm, dan tingginya 12 cm. Volume balok tersebut adalah .... cm³",
                Arrays.asList(
                        "4.700",
                        "4.702",
                        "4.704",
                        "4.706"
                ),
                3
        ));

        kuisList.add(new KuisItem(
                "Sebuah kubus memiliki rusuk yang panjangnya 15 cm. Volume dan luas permukaan kubus tersebut adalah ....",
                Arrays.asList(
                        "V = 3.275 cm³ dan Luas = 1.250 cm²",
                        "V = 3.375 cm³ dan Luas = 1.350 cm²",
                        "V = 3.385 cm³ dan Luas = 1.400 cm²",
                        "V = 3.395 cm³ dan Luas = 1.450 cm²"
                ),
                2
        ));

        kuisList.add(new KuisItem(
                "Luas suatu persegi panjang adalah 196cm. Panjang sisi persegi panjang tersebut adalah ....",
                Arrays.asList(
                        "12 cm",
                        "14 cm",
                        "20 cm",
                        "49 cm"
                ),
                4
        ));

        kuisList.add(new KuisItem(
                "Berapakah keliling dari sebuah lingkaran jika diketahui memiliki jari-jari 25cm ....",
                Arrays.asList(
                        "78",
                        "125",
                        "157",
                        "203"
                ),
                3
        ));

        kuisList.add(new KuisItem(
                "Sebuah Tabung memiliki jari-jari dengan 7cm dan tinggi 50cm. Luas permukaan pada tabung tersebut adalah ....",
                Arrays.asList(
                        "2505",
                        "4302",
                        "3780",
                        "2510"
                ),
                1
        ));

        kuisList.add(new KuisItem(
                "Dibawah ini bangun datar yang memiliki sisi sama panjang adalah ....",
                Arrays.asList(
                        "Trapesium",
                        "Limas",
                        "Layang-Layang",
                        "Belah Ketupat"
                ),
                4
        ));

        kuisList.add(new KuisItem(
                "Diketahui sebuah trapesium memiliki sisi atas 7cm, sisi miring pertama 10cm, sisi bawah trapesium 18cm, tinggi trapesium 9cm dan sisi miring kedua 10cm. Berapakah luas trapesium tersebut?",
                Arrays.asList(
                        "104",
                        "135",
                        "202",
                        "112"
                ),
                4
        ));

        kuisList.add(new KuisItem(
                "Keliling sebuah segitiga sama kaki adalah 36cm. Jika panjang alasnya 10cm, maka panjang sisi yang lain itu adalah ....",
                Arrays.asList(
                        "26",
                        "18",
                        "13",
                        "24"
                ),
                3
        ));

        kuisList.add(new KuisItem(
                "Alas sebuah limas beraturan berbentuk persegi dengan panjang sisi 5 cm dan tinggi segitiga bidang tegaknya 10 cm. Luas permukaan limas tersebut adalah ....",
                Arrays.asList(
                        "75",
                        "100",
                        "125",
                        "150"
                ),
                3
        ));

        kuisList.add(new KuisItem(
                "Keliling sebuah kebun 160 m. Jika panjang kebun 50 m, maka lebar kebun tersebut adalah ....",
                Arrays.asList(
                        "30",
                        "35",
                        "40",
                        "45"
                ),
                1
        ));

        KuisAdapter kuisAdapter = new KuisAdapter(kuisList);
        rvKuis.setAdapter(kuisAdapter);
        rvKuis.setHasFixedSize(true);

        fabBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        fabFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<KuisItem> kuisList = kuisAdapter.getKuisList();

                int score = 0;
                for (KuisItem kuisItem : kuisList) {
                    if (kuisItem.getAnswerId() == 0) {
                        Toast.makeText(KuisActivity.this, "Harap isi semua jawaban", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (kuisItem.getAnswerId() == kuisItem.getCorrectAnswerId()) {
                        score += 10;
                    }
                }

                Map<String, Object> data = new HashMap<>();
                data.put("name", nama);
                data.put("class", kelas);
                data.put("score", score);

                int finalScore = score;
                firebaseFirestore.collection("users").document(firebaseAuth.getUid()).set(data, SetOptions.merge())
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(KuisActivity.this, "Jawaban disimpan", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(KuisActivity.this, HasilKuisActivity.class);
                                intent.putExtra("nilai", finalScore);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(KuisActivity.this, "Jawaban gagal disimpan. Periksa koneksi internet", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}