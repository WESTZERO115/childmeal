package com.example.childmeal;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Frag_mypage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Frag_mypage extends Fragment {

    private static String kidName;
    private static String cardNum;
    private static int childAge;
    private static int childHeight;
    private static int childWeight;
    private static String childGender;
    private Float kcal;
    private Float carbon;
    private Float protein;
    private Float fat;
    private Float natrium;


    private View view;
    private Button food_image_btn;
    private Button logout_btn;
    TextView mypage_name;
    TextView mypage_card;
    TextView mypage_age;
    TextView mypage_height;
    TextView mypage_weight;
    TextView mypage_gender;


    EditText mypage_kcal;
    EditText mypage_carbon;
    EditText mypage_protein;
    EditText mypage_fat;
    EditText mypage_natrium;

    String[] ate_food_eatmeal = new String[100];



    double[] kimbap_nutrition = {158.0, 28.8,3.6, 3.2, 417};
    double[] donkas_nutrition = {22.77, 2.10, 0.8, 1.3, 30.15};
    double[] tteokboggi_nutrition = {176.52, 36.99, 3.8, 2.16, 478.3};

    double[] recommended_eleven = {1802.1, 48.26, 62.95, 62.95, 2422.99};
    double[] today_child_nutrition = {0, 0, 0, 0,0};


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Frag_mypage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Frag_mypage.
     */
    // TODO: Rename and change types and number of parameters
    public static Frag_mypage newInstance(String param1, String param2) {
        Frag_mypage fragment = new Frag_mypage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_frag_mypage,container,false);
        /*
        Socket_Python socket_python = new Socket_Python();
        socket_python.connect();
        */
        AWS_RDS aws = new AWS_RDS();
        aws.mypage_lack();



        aws.set_ate_food(ate_food_eatmeal);
        Log.v("태그","마이페이지 화면에서 aws 함수 실행함");

        food_image_btn = (Button) view.findViewById(R.id.food_image_btn_);
        logout_btn = (Button) view.findViewById(R.id.btn_logout);
        mypage_name = (TextView) view.findViewById(R.id.mypage_name);
        mypage_name.setText(kidName);

        mypage_card = (TextView) view.findViewById(R.id.mypage_cardNum);
        mypage_card.setText("카드 번호: "+cardNum);
        mypage_age = (TextView) view.findViewById(R.id.mypage_childAge);
        mypage_age.setText("만 나이: "+(childAge));
        mypage_height = (TextView) view.findViewById(R.id.mypage_childHeight);
        mypage_height.setText("키: "+(childHeight));
        mypage_weight = (TextView) view.findViewById(R.id.mypage_childWeight);
        mypage_weight.setText("몸무게: "+String.valueOf(childWeight));
        mypage_gender = (TextView) view.findViewById(R.id.mypage_childGender);
        mypage_gender.setText("성별: "+childGender);




        for (int i=0; i<2;i++){
            if(ate_food_eatmeal[i]=="김밥"){
                for (int j=0; j<5; j++){
                    today_child_nutrition[j] += kimbap_nutrition[j];
                }
            }
            else if(ate_food_eatmeal[i]=="돈까스"){
                for (int j=0; j<5; j++){
                    today_child_nutrition[j] += donkas_nutrition[j];
                }
            }
            else if(ate_food_eatmeal[i]=="떡볶이"){
                for (int j=0; j<5; j++){
                    today_child_nutrition[j] += tteokboggi_nutrition[j];
                }
            }
        }
        Log.v("태그","recommend_eleven[0]:"+recommended_eleven[0]);
        Log.v("태그","today_child_nutrition[0]:"+today_child_nutrition[0]);
        mypage_kcal = (EditText) view.findViewById(R.id.editText_kcal);
        mypage_kcal.setText((recommended_eleven[0] - kimbap_nutrition[0])+"칼로리 필요");
        //mypage_kcal.setText("섭취 기록이 없습니다.");
        mypage_carbon = (EditText) view.findViewById(R.id.editText_carbon);
        mypage_carbon.setText((recommended_eleven[1] - kimbap_nutrition[1])+"탄수화물 필요");
        //mypage_carbon.setText("섭취 기록이 없습니다.");
        mypage_protein = (EditText) view.findViewById(R.id.editText_protein);
        mypage_protein.setText((recommended_eleven[2] - kimbap_nutrition[2])+"단백질 필요");
        //mypage_protein.setText("섭취 기록이 없습니다.");
        mypage_fat = (EditText) view.findViewById(R.id.editText_fat);
        mypage_fat.setText((recommended_eleven[3] - kimbap_nutrition[3])+"지방 필요");
        //mypage_fat.setText("섭취 기록이 없습니다.");
        mypage_natrium = (EditText) view.findViewById(R.id.editText_natrium);
        mypage_natrium.setText((recommended_eleven[4] - kimbap_nutrition[4])+"나트륨 필요");
        //mypage_natrium.setText("섭취 기록이 없습니다.");


        food_image_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Food_image.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Login_and_SignUp.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        //return inflater.inflate(R.layout.fragment_frag_mypage, container, false);
        return view;
    }

    public static void get_childName(String kid_Name){
        kidName = kid_Name;
    }
    public static void get_cardNum(String card_Num){ cardNum = card_Num; }
    public static void get_childAge(int child_age){ childAge = child_age;}
    public static void get_childHeight(int child_height){ childHeight = child_height;}
    public static void get_childWeight(int child_weight){ childWeight = child_weight;}
    public static void get_childGender(String child_gender){ childGender = child_gender;}
}