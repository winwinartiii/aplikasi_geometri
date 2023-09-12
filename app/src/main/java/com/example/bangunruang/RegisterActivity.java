package com.example.bangunruang;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    private EditText etEmail;
    private EditText etPassword;
    private Spinner spinnerTipe;
    private Button btnRegister;
    private LinearLayout llLogin;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        spinnerTipe = findViewById(R.id.spinnerTipe);
        btnRegister = findViewById(R.id.btnRegister);
        llLogin = findViewById(R.id.llLogin);
        progressBar = findViewById(R.id.progressBar);

        String[] tipeList = {"murid", "guru"};
        ArrayAdapter<String> tipeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, tipeList);
        spinnerTipe.setAdapter(tipeAdapter);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                if (email.isEmpty()) {
                    etEmail.setError("Email tidak boleh kosong");
                    return;
                }

                if (!isValidEmail(email)) {
                    etEmail.setError("Email tidak valid");
                    return;
                }

                String password = etPassword.getText().toString();
                if (password.isEmpty()) {
                    etPassword.setError("Password tidak boleh kosong");
                    return;
                }

                if (password.length() < 6) {
                    etPassword.setError("Password harus lebih dari 5 karakter");
                    return;
                }

                String userType = (String) spinnerTipe.getSelectedItem();
                register(email, password, userType);
            }
        });

        llLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private Boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void register(String email, String password, String userType) {
        progressBar.setVisibility(View.VISIBLE);

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        saveData(authResult, userType);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBar.setVisibility(View.INVISIBLE);

                        if (e instanceof FirebaseAuthUserCollisionException) {
                            Toast.makeText(RegisterActivity.this, "Email telah digunakan", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Toast.makeText(RegisterActivity.this, "Autentikasi gagal. Silakan coba lagi", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void saveData(AuthResult authResult, String userType) {
        String userId = authResult.getUser().getUid();

        Map<String, String> data = new HashMap<>();
        data.put("userType", userType);

        firebaseFirestore.collection("users").document(userId).set(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        progressBar.setVisibility(View.INVISIBLE);

                        SharedPrefs prefs = new SharedPrefs(RegisterActivity.this);
                        prefs.setUserType(userType);

                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        authResult.getUser().delete();
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(RegisterActivity.this, "Autentikasi gagal. Silakan coba lagi", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}