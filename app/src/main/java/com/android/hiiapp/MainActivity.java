package com.android.hiiapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.hiiapp.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
        setListener();
    }

    private void reload() {

    }

    private void setListener() {
        binding.textRegister.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, registerActivity.class);
            startActivity(intent);
        });
        binding.signInUsingPhone.setOnClickListener(view -> {
            Intent intent =  new Intent(MainActivity.this, loginUsingPhone.class);
            startActivity(intent);
        });
    binding.loginBtn.setOnClickListener(
        view -> {
          if (!validateEmail() || !validatePassword()) {
            return;
          } else {
            mAuth
                .signInWithEmailAndPassword(
                    binding.textEditEmail.getEditText().getText().toString(),
                    binding.textEditPassword.getEditText().getText().toString())
                .addOnCompleteListener(
                    this,
                        task -> {
                          if (task.isSuccessful()) {
                            Intent intent = new Intent(MainActivity.this, home.class);
                            startActivity(intent);
                          } else {
                            Toast.makeText(MainActivity.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                          }
                        });
          }
        });
    }
    private boolean validateEmail(){
        String isEmail = binding.textEditEmail.getEditText().getText().toString().trim();
        if (isEmail.isEmpty()){
            binding.textEditEmail.setError("Field can't be empty!");
            return false;
        }else{
            binding.textEditEmail.setError(null);
            binding.textEditEmail.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validatePassword(){
        String isPassword = binding.textEditPassword.getEditText().getText().toString().trim();
        if (isPassword.isEmpty()){
            binding.textEditPassword.setError("Field can't be empty!");
            return false;
        }else{
            binding.textEditPassword.setError(null);
            binding.textEditPassword.setErrorEnabled(false);
            return true;
        }
    }
}