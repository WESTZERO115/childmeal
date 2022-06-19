package com.example.childmeal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignUp_Detail_Kid extends AppCompatActivity {
    EditText et_kidName;
    EditText et_kidAge;
    EditText et_Height;
    EditText et_Weight;
    RadioGroup rg_gender;
    RadioButton radio_btn_boy;
    RadioButton radio_btn_girl;

    Button sign_up_clear;
    Button back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_detail_kid);

        et_kidName = (EditText) findViewById(R.id.et_kidName);
        et_kidAge = (EditText) findViewById(R.id.et_kid_age);
        et_Height = (EditText) findViewById(R.id.et_height);
        et_Weight = (EditText) findViewById(R.id.et_weight);
        rg_gender = (RadioGroup) findViewById(R.id.radio_gender);
        radio_btn_boy = (RadioButton) findViewById(R.id.radio_btn_boy);
        radio_btn_girl = (RadioButton) findViewById(R.id.radio_btn_girl);

        sign_up_clear = (Button) findViewById(R.id.btn_signup_clear);
        back = (Button) findViewById(R.id.btn_back);

        sign_up_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("태그", "회원가입하기 버튼 눌렀음");
                add_to_DB_child(); }
        });



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("태그", "뒤로가기 버튼 눌렀음");
                startActivity(new Intent(SignUp_Detail_Kid.this,Login_and_SignUp_Kid.class));
            }
        });
    }

    public void add_to_DB_child(){
        Log.v("태그", "add_to_db_child 실행됨");
        /*String kidName = et_kidName.getText().toString();
        String kidAge_ = et_kidAge.getText().toString();
        String height_ = et_Height.getText().toString();
        String weight_ = et_Weight.getText().toString();
        int id = radioGroup.getCheckedRadioButtonId();
        RadioButton chosen_button = (RadioButton) findViewById(id);
        String gender = chosen_button.getText().toString();

        Log.v("태그", "변수들");



        if (kidName.isEmpty()) {
            et_kidName.setError("빈칸을 채워주세요.");
        }
        else if(kidAge_.isEmpty()){
            et_kidAge.setError("빈칸을 채워주세요.");
        }
        else if(height_.isEmpty()){
            et_Height.setError("빈칸을 채워주세요.");
        }
        else if(kidAge_.isEmpty()){
            et_kidAge.setError("빈칸을 채워주세요.");
        }
        else{
            int kidAge = Integer.parseInt(kidAge_);
            int height = Integer.parseInt(height_);
            int weight = Integer.parseInt(weight_);
            AWS_RDS.addTemp(kidName,kidAge,height,weight,gender);
            Log.v("태그", "addTemp 실행됨");
            startActivity(new Intent(SignUp_Detail_Kid.this, MainActivity.class));
        }*/

        String kidName = et_kidName.getText().toString();
        int kidAge = Integer.parseInt(et_kidAge.getText().toString());
        int height = Integer.parseInt(et_Height.getText().toString());
        int weight = Integer.parseInt(et_Weight.getText().toString());
        int id = rg_gender.getCheckedRadioButtonId();
        RadioButton chosen_button = (RadioButton) findViewById(id);
        String gender = chosen_button.getText().toString();

        AWS_RDS.addTemp(kidName,kidAge,height,weight,gender);
        Log.v("태그", "addTemp 수행됨");
        Frag_mypage.get_childName(kidName);
        Frag_mypage.get_childAge(kidAge);
        Frag_mypage.get_childHeight(height);
        Frag_mypage.get_childWeight(weight);
        Frag_mypage.get_childGender(gender);

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        // startActivity(new Intent(SignUp_Detail_Kid.this, MainActivity.class));
        Log.v("태그", "add_to_DB 함수 끝");
    }

}
