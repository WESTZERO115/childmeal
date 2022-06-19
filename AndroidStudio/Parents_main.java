package com.example.childmeal;

import androidx.annotation.NonNull;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.widget.ListView;
import android.util.Base64;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Parents_main extends AppCompatActivity {
    private static String parents_id;
    private static String parents_pw;
    private Button btn_add_child;

    private static String[] childName = {"0", "0", "0"};
    private static int[] childNum = {0, 0, 0};
    private static int pointer = 0;
    private ListView list;

    protected void OnCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parents_main);
        Log.v("태그", "부모 메인 화면 떠있음");

        btn_add_child = (Button) findViewById(R.id.btn_register_by_cardnum);

        list = (ListView)findViewById(R.id.list_child); /////


        List<String> data = new ArrayList<>();


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, data);
        list.setAdapter(arrayAdapter);

        data.add("aa");
        data.add("bb");
        arrayAdapter.notifyDataSetChanged();




        for (int i=0; i<3; i++){
            if(childNum[i]!=0){
                data.add("카드 번호:"+childNum[i]+" / 이름:"+childName[i]);
                arrayAdapter.notifyDataSetChanged();
            }
            else{
                data.add("아동을 등록해 주세요.");
                arrayAdapter.notifyDataSetChanged();
            }
        }

        btn_add_child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Parents_main.this, Parents_addchild.class));
            }
        });
    }
    public static void get_parentsId (String parents_Id) {parents_id = parents_Id;}
    public static void get_parentsPw (String parents_Pw) {parents_pw = parents_Pw;}

    //public static void get_childName (String name) {childName[pointer] = name;}
    //public static void get_childNum (int num) {childNum[pointer] = num; pointer++;}
}
