package com.example.bangunruang;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class NilaiKuisActivity extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;

    private RecyclerView rvNilai;
    private FloatingActionButton fabBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nilai_kuis);

        firebaseFirestore = FirebaseFirestore.getInstance();

        rvNilai = findViewById(R.id.rvNilai);
        fabBack = findViewById(R.id.fabBack);

        firebaseFirestore.collection("users").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                List<NilaiItem> nilaiList = new ArrayList<>();

                if (value != null) {
                    for (QueryDocumentSnapshot documentSnapshot : value) {
                        String userType = documentSnapshot.getString("userType");
                        String name = documentSnapshot.getString("name");
                        if (userType.equals("murid") && name != null) {
                            String className = documentSnapshot.getString("class");
                            Integer score = documentSnapshot.getLong("score").intValue();
                            nilaiList.add(new NilaiItem(name, className, score));
                        }
                    }
                }

                NilaiAdapter nilaiAdapter = new NilaiAdapter(nilaiList);
                rvNilai.setAdapter(nilaiAdapter);
            }
        });

        fabBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}