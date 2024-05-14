package com.example.kinolenta.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.kinolenta.R;
import com.example.kinolenta.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.singUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.emailEts.getText().toString().isEmpty() || binding.usernameEts.getText().toString().isEmpty() || binding.passwordEts.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Поля не могут быть пустыми", Toast.LENGTH_LONG).show();
                } else {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(binding.emailEts.getText().toString(), binding.passwordEts.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        HashMap<String, String> userInfo = new HashMap<>();
                                        userInfo.put("email", binding.emailEts.getText().toString());
                                        userInfo.put("username", binding.usernameEts.getText().toString());
                                        FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(userInfo);
                                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                    }
                                }
                            });

                }
            }
        });
    }
}