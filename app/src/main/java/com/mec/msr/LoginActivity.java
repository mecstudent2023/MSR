package com.mec.msr;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mec.msr.myclasses.MyDBHelperUser;
import com.mec.msr.myclasses.MyUser;

public class LoginActivity extends AppCompatActivity {


    private EditText editTextEmail;
    private EditText editTextPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        Button buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkEntries();
            }
        });

        Button buttonRegister = findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });


    }


    private void checkEntries() {
        String pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        MyDBHelperUser dbHelper = new MyDBHelperUser(LoginActivity.this);


        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        MyUser dbUser = dbHelper.findUser(email, password);


        if (email.length() == 0) {
            Toast.makeText(LoginActivity.this, "Please write your email", Toast.LENGTH_SHORT).show();
        } else if (!email.matches(pattern)) {
            Toast.makeText(LoginActivity.this, "Invalid email address", Toast.LENGTH_SHORT).show();
        } else {

            if (password.length() == 0) {
                Toast.makeText(LoginActivity.this, "Please write your password", Toast.LENGTH_SHORT).show();
            } else if (password.length() < 6) {
                Toast.makeText(LoginActivity.this, "invalid password", Toast.LENGTH_SHORT).show();
            } else if (!email.equals(dbUser.getEmail()) && !password.equals(dbUser.getPassword())) {
                Toast.makeText(LoginActivity.this, "Your credentials doesn't match any record", Toast.LENGTH_SHORT).show();
            } else {
                login(dbUser.getId());
            }
        }


    }

    private void login(int userId) {
        saveUserId(userId);
        Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(mainIntent);
        finish();
    }

    private void saveUserId(int userId) {
        SharedPreferences sharedPref = getSharedPreferences("msr_pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("user_id", userId);
        editor.apply();
    }


}