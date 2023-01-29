package com.android.hiiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.android.hiiapp.databinding.ActivityRegisterBinding;
public class registerActivity extends AppCompatActivity {
     ActivityRegisterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListener();
    }

    private void setListener() {
        binding.textBtnLogin.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
        binding.registerBtn.setOnClickListener(view -> {
            if (validateEmail()){
                return;
            }else{

            }
        });
        binding.signInUsingPhone.setOnClickListener(view -> {
            startActivity(new Intent(this, loginUsingPhone.class));
        });
    }
    private boolean validateEmail(){
        String isEmail = binding.textEditEmail.getEditText().getText().toString().trim();
        if (isEmail.isEmpty()){
            binding.textEditEmail.setError("Field can't be empty!");
            return false;
        } else{
            binding.textEditEmail.setError(null);
            binding.textEditEmail.setErrorEnabled(false);
            return true;
        }
    }
}