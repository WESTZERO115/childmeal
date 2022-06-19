package com.example.childmeal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Login_and_SignUp_Parents extends AppCompatActivity {
    EditText et_id;
    EditText et_pw;
    Button btn_login;
    Button btn_signup;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_and_signup_parents);

        et_id = (EditText) findViewById(R.id.et_id);
        et_pw = (EditText) findViewById(R.id.et_pw);
        btn_login = (Button) findViewById(R.id.btn_parent_login);
        btn_signup = (Button) findViewById(R.id.btn_parent_signup);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFunc();
            }
        });
    }

    public void addFunc(){
        String id_parents = et_id.getText().toString();
        String pw_parents = et_pw.getText().toString();
        if (id_parents.isEmpty()) {
            et_id.setError("빈칸을 채워주세요.");
        }
        else if(pw_parents.isEmpty()){
            et_pw.setError("빈칸을 채워주세요.");
        }
        else {
            AWS_RDS.add_parents(id_parents, pw_parents);
            Log.v("태그", "addParents 수행됨");

            Parents_main.get_parentsId(id_parents);
            Parents_main.get_parentsPw(pw_parents);

            Parents_addchild.get_parentsId(id_parents);
            Parents_addchild.get_parentsPw(pw_parents);

            Intent intent = new Intent(getApplicationContext(), Parents_main.class);
            startActivity(intent);
        }
    }
}
