package com.mec.msr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mec.msr.myclasses.MyDBHelperUser;
import com.mec.msr.myclasses.MyUser;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextFullName;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        editTextFullName = findViewById(R.id.editTextFullName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);

        Button buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button buttonRegister = findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkEntries();
            }
        });


    }


    private void checkEntries() {
        String pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        String fullName = editTextFullName.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        String confirmPassword = editTextConfirmPassword.getText().toString();


        if (fullName.length() == 0) {
            Toast.makeText(RegisterActivity.this, "Please write your full name", Toast.LENGTH_SHORT).show();
        } else if (email.length() == 0) {
            Toast.makeText(RegisterActivity.this, "Please write your email", Toast.LENGTH_SHORT).show();
        } else if (!email.matches(pattern)) {
            Toast.makeText(RegisterActivity.this, "Invalid email address", Toast.LENGTH_SHORT).show();
        } else {

            if (password.length() == 0) {
                Toast.makeText(RegisterActivity.this, "Please write your password", Toast.LENGTH_SHORT).show();
            } else if (password.length() < 6) {
                Toast.makeText(RegisterActivity.this, "invalid password", Toast.LENGTH_SHORT).show();
            } else if (confirmPassword.length() == 0) {
                Toast.makeText(RegisterActivity.this, "Please write your confirm password", Toast.LENGTH_SHORT).show();
            } else if (confirmPassword.length() < 6) {
                Toast.makeText(RegisterActivity.this, "invalid confirm password", Toast.LENGTH_SHORT).show();
            } else if (!confirmPassword.equals(password)) {
                Toast.makeText(RegisterActivity.this, "confirm password is not equal to password", Toast.LENGTH_SHORT).show();
            } else {
                register(fullName, email, password);
            }
        }


    }

    private void register(String fullName, String email, String password) {
        MyDBHelperUser dbHelper = new MyDBHelperUser(RegisterActivity.this);
        dbHelper.addUser(new MyUser(fullName, email, password));

        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();

    }

}