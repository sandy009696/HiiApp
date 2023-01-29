package com.android.hiiapp;

import static androidx.core.view.ViewGroupKt.isEmpty;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.android.hiiapp.databinding.ActivityLoginUsingPhoneBinding;
public class loginUsingPhone extends AppCompatActivity {
    ActivityLoginUsingPhoneBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginUsingPhoneBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListener();
    }

    private void setListener() {
    binding.textBtnLogin.setOnClickListener(view -> {
            Intent intent = new Intent(loginUsingPhone.this, MainActivity.class);
            startActivity(intent);
        });
    binding.registerBtn.setOnClickListener(
        view -> {
            if (!validateNumber()){
                return;
            }else{
                Intent intent = new Intent(loginUsingPhone.this, otpVerification.class);
                startActivity(intent);
            }
        });
    }
    private boolean validateNumber(){
        String isPhone = binding.textEditNumber.getEditText().getText().toString().trim();
        if (isPhone.isEmpty()){
            binding.textEditNumber.setError("Field can't be empty!");
            return false;
        }else{
            binding.textEditNumber.setError(null);
            binding.textEditNumber.setErrorEnabled(false);
            return true;
        }
    }
}