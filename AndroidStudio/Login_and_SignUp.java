package com.example.childmeal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Login_and_SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_and_signup);

        Button button_kid_login = (Button) findViewById(R.id.button_kid_login);
        button_kid_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login_and_SignUp_Kid.class);
                startActivity(intent);
            }
        });

        Button button_parent_login = (Button) findViewById(R.id.button_parent_login);
        button_parent_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login_and_SignUp_Parents.class);
                startActivity(intent);
            }
        });
    }
}
