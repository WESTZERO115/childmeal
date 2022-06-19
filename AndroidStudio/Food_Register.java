package com.example.childmeal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Food_Register extends AppCompatActivity {
    EditText et_regi_food;
    Button back, register;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_register);

        et_regi_food = (EditText) findViewById(R.id.et_registerFood);
        back = (Button) findViewById(R.id.btn_register_back);
        register = (Button) findViewById(R.id.btn_register);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Food_Register.this, Food_image.class));
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String registered_food = et_regi_food.getText().toString();
                AWS_RDS.add_food(registered_food);
                startActivity(new Intent(Food_Register.this, MainActivity.class));
            }
        });
    }
}
