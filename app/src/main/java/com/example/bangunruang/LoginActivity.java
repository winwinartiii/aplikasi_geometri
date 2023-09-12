package com.example.bangunruang;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    private EditText etEmail;
    private EditText etPassword;
    private TextView tvForgotPassword;
    private Button btnLogin;
    private LinearLayout llRegister;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        btnLogin = findViewById(R.id.btnLogin);
        llRegister = findViewById(R.id.llRegister);
        progressBar = findViewById(R.id.progressBar);

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                if (email.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Email tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isValidEmail(email)) {
                    Toast.makeText(LoginActivity.this, "Email tidak valid", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.sendPasswordResetEmail(email)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(LoginActivity.this, "Email berhasil dikirim. Silakan cek kotak masuk email anda", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginActivity.this, "Email gagal dikirim. Silakan cek kembali email anda", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
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

                login(email, password);
            }
        });

        llRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private Boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void login(String email, String password) {
        progressBar.setVisibility(View.VISIBLE);

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        getData(authResult.getUser().getUid());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        e.printStackTrace();
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(LoginActivity.this, "Autentikasi gagal. Silakan coba lagi", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void getData(String uid) {
        firebaseFirestore.collection("users").document(uid).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        progressBar.setVisibility(View.INVISIBLE);

                        String userType = documentSnapshot.getString("userType");
                        SharedPrefs prefs = new SharedPrefs(LoginActivity.this);
                        prefs.setUserType(userType);

                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        e.printStackTrace();
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(LoginActivity.this, "Autentikasi gagal. Silakan coba lagi", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}