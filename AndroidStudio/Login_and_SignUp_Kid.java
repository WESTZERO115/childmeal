package com.example.childmeal;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Login_and_SignUp_Kid extends AppCompatActivity {
    EditText et_cardNum;
    Button btn_kid_login;
    Button btn_kid_signup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_and_signup_kid);

        et_cardNum = (EditText) findViewById(R.id.et_cardNum);
        btn_kid_signup = (Button) findViewById(R.id.btn_kid_signup);
        btn_kid_login = (Button) findViewById(R.id.btn_kid_login);


        btn_kid_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { addFunc(); }

        });
        btn_kid_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { checkFunc(); }
        });
    }


    // ---------- 01. 회원 가입 버튼을 눌렀을 때
    public void addFunc() {
        String cardNum = et_cardNum.getText().toString();
        if (cardNum.isEmpty()) {
            et_cardNum.setError("빈칸을 채워주세요.");
        }
        else if(et_cardNum.length()<12){
            et_cardNum.setError("12자리를 채워서 작성해 주세요.");
        }
        else if(et_cardNum.length()>12){
            et_cardNum.setError("12자리를 넘었습니다.");
        }
        else {
            AWS_RDS.set_cardNum(cardNum);
            Frag_mypage.get_cardNum(cardNum);
            try{
                startActivity(new Intent(Login_and_SignUp_Kid.this, SignUp_Detail_Kid.class));
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    // ---------- 02. 로그인 버튼을 눌렀을 때
    public void checkFunc(){
        String cardNum = et_cardNum.getText().toString();
        if (cardNum.isEmpty()) {
            et_cardNum.setError("빈칸을 채워주세요.");
        }
        else if(et_cardNum.length()<12){
            et_cardNum.setError("12자리를 채워서 작성해 주세요.");
        }
        else if(et_cardNum.length()>12){
            et_cardNum.setError("12자리를 넘었습니다.");
        }
        else{
            AWS_RDS.set_cardNum(cardNum);
            Frag_mypage.get_cardNum(cardNum);
            AWS_RDS.login();
            try{
                startActivity(new Intent(Login_and_SignUp_Kid.this, MainActivity.class));
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

}