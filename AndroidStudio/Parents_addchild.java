package com.example.childmeal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Parents_addchild extends AppCompatActivity {
    private static String parents_id;
    private static String parents_pw;

    EditText parents_childnum;
    EditText parents_childname;
    Button register_child;

    protected void OnCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parents_addchild);

        parents_childname = (EditText) findViewById(R.id.et_parents_childname);
        parents_childnum = (EditText) findViewById(R.id.et_parents_childnum);
        register_child = (Button) findViewById(R.id.btn_register_child);

        register_child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register_children();
            }
        });

    }
    public void register_children() {
        String childname = parents_childname.getText().toString();
        int childnum = Integer.parseInt(parents_childnum.getText().toString());
        if(childname.isEmpty()){
            parents_childname.setError("빈칸을 채워주세요.");
        }
        else if((int)( Math.log10(childnum)+1 )==0){
            parents_childnum.setError("빈칸을 채워주세요.");
        }
        else if((int)( Math.log10(childnum)+1 )<12){
            parents_childnum.setError("12자리를 채워서 작성해 주세요.");
        }
        else if((int)( Math.log10(childnum)+1 )>12){
            parents_childnum.setError("12자리를 넘었습니다.");
        }
        else{
            AWS_RDS.add_parents_child(childnum, parents_id);
            //Parents_main.get_childName(childname);
            //Parents_main.get_childNum(childnum);
        }
    }

    public static void get_parentsId (String parents_Id) {parents_id = parents_Id;}
    public static void get_parentsPw (String parents_Pw){
        parents_pw = parents_Pw;
    }
}
