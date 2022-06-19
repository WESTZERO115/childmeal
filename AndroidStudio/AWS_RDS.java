package com.example.childmeal;

import android.content.Context;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

// 이 자바 파일은 AWS MySQL RDS와의 모든 통신을 담당하는 곳입니다.

public class AWS_RDS extends AppCompatActivity {
    public static final String DATABASE_NAME = "capstonedesign";
    public static final String url = "jdbc:mysql://capstonedesign.c3lmane8vo1i.ap-northeast-2.rds.amazonaws.com:3306/"+DATABASE_NAME;
    public static final String username = "admin", password = "swusw2022";
    public static final String TABLE_NAME = "CHILD";
    private static String cardNum;

    private static String GEOCODE_URL="http://dapi.kakao.com/v2/local/search/address.json?query=";
    private static String GEOCODE_USER_INFO="KakaoAK f98ed1b81206124c760c5195b0db41f1"; //KakaoAK + 발급받은 rest api key

    URL obj;

    String x;
    String y;
    String[] stores_address = new String[5926]; // 가맹점 리스트 (store_address만 담겨 있음)


    String[] X = new String[5926]; // 위도 배열
    String[] Y = new String[5926]; // 경도 배열
    String[] Store_names = new String[5926];
    String[] ate_food_eatmeal = new String[100];

    public void setX(String[] x) {
        //x = X;
        for (int i=0; i<200; i++){
            x[i] = X[i];
        }
    }
    public void setY(String[] y) {
        //y = Y;
        for (int i=0; i<200; i++){
            y[i] = Y[i];
        }
    }
    public void set_store_name(String[] store_names){
        for (int i=0; i<200; i++){
            store_names[i] = Store_names[i];
        }
    }

    public void set_ate_food(String[] ate_foods){
        mypage_lack();

        for(int i=0; i<100; i++){
            ate_foods[i] = ate_food_eatmeal[i];
        }
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static void set_cardNum(String a){
        cardNum = a;
    }

    // ---------- 01. 회원 가입 버튼을 눌렀을 때 실행하는 함수 (AWS MySQL DB에 등록)
    public static void addTemp(String kidName, int kidAge, int height, int weight, String gender) {
        new Thread(() -> {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Log.v("태그", "드라이버 접근");
                Connection connection = DriverManager.getConnection(url, username, password);
                Log.v("태그", "연결");
                Statement statement = connection.createStatement();

                // add to RDS DB:
                Log.v("태그", "INSERT INTO 하기 직전");
                statement.execute("INSERT INTO " + TABLE_NAME + " VALUES("+ cardNum +
                        ", '"+kidName+"', "+kidAge+", "+height+", "+weight+", '"+gender+"')");
                Log.v("태그", "INSERT INTO 완료");
                connection.close();

            } catch (Exception e) {
                e.printStackTrace();
                Log.v("태그", "왜안돼 시발 이건 addTemp catch문입니다.");
            }
        }).start();
    }


    // ---------- 02. 로그인 버튼을 눌렀을 때 실행하는 함수 (AWS MySQL DB로부터 가져옴)
    public static void login(){
        new Thread(() -> {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();
                Log.v("태그", "이제 DB에 해당 카드 번호 있나 확인해봄");

                ResultSet rs = statement.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE CardNum ="+" '"+cardNum+"'");
                Log.v("태그", "(로그인)번호 있나 확인했음");
                while (rs.next()) {
                    String childname = rs.getString("ChildName");
                    String childage = rs.getString("ChildAge");
                    String height = rs.getString("ChildHeight");
                    String weight = rs.getString("ChildWeight");
                    String gender = rs.getString("ChildGender");

                    Frag_mypage.get_childName(childname);
                    Frag_mypage.get_childAge(Integer.parseInt(childage));
                    Frag_mypage.get_childHeight(Integer.parseInt(height));
                    Frag_mypage.get_childWeight(Integer.parseInt(weight));
                    Frag_mypage.get_childGender(gender);
                }

                connection.close();

            } catch (Exception e) {
                e.printStackTrace();
                Log.v("태그", "login catch문입니다.");
            }
        }).start();
    }

    // ---------- 아동 - 마이페이지에서 식단을 기록하는 함수
    public static void add_food(String food){
        new Thread(() -> {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Log.v("태그", "드라이버 접근");
                Connection connection = DriverManager.getConnection(url, username, password);
                Log.v("태그", "연결");
                Statement statement = connection.createStatement();

                // add to RDS DB:
                Log.v("태그", "food_image_ INSERT INTO 하기 직전");
                System.out.println("cardNum은 :"+cardNum);
                System.out.println("food는 :"+food);

                // -- 현재 날짜
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN);
                String days = sdf1.format (System.currentTimeMillis());
                //println(days);

                // -- 현재 시간
                SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss", Locale.KOREAN);
                String time = sdf2.format (System.currentTimeMillis());
                // println(time);

                statement.execute("INSERT INTO EATMEAL VALUES("+ cardNum +
                        ", '"+food+"', '"+days+" "+time+"')");

                Log.v("태그", "food_image_ INSERT INTO 부분");
                connection.close();

            } catch (Exception e) {
                e.printStackTrace();
                Log.v("태그", "add_food catch문입니다.");
            }
        }).start();
    }

    // ---------- 아동 - 지도에 가맹점 마커 찍기 위해 위도, 경도를 보내주는 함수
    public void marker(){
        new Thread(()->{
            try{
                // <-------------> AWS RDS - STORELIST 연결 부분입니다.
                Log.v("태그", "marker 실행 시작");
                Class.forName("com.mysql.jdbc.Driver");
                Log.v("태그", "드라이버 접근");

                Connection connection = DriverManager.getConnection(url, username, password);
                Log.v("태그", "연결");

                Statement statement = connection.createStatement();
                Log.v("태그", "연결2");
                // check with RDS DB:
                ResultSet rs = statement.executeQuery("SELECT * FROM STORELIST");
                Log.v("태그", "STORELIST 가져옴");
                int pointer = 0;
                while(rs.next()){
                    if(pointer>5926) {break;}
                    //Log.v("태그", "rs.getString(X)"+rs.getString("X"));
                    //Log.v("태그", "rs.getString(Y)"+rs.getString("Y"));
                    X[pointer] = rs.getString("X");
                    Y[pointer] = rs.getString("Y");
                    Store_names[pointer] = rs.getString("store_name");
                    pointer++;
                }
                Log.v("태그", "X는 "+X[0]);
                Log.v("태그", "X[1]는 "+X[1]);
                Log.v("태그", "Y는 "+Y[0]);
                Log.v("태그", "Y[1]는 "+Y[1]);

                Frag_home home = new Frag_home();
                home.setstart();

            }catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }).start();
    }

    // ---------- 부모 - 회원 가입 버튼을 눌렀을 때 실행하는 함수 (AWS MySQL DB에 등록)
    public static void add_parents(String parents_id, String parents_password){
        new Thread(()->{
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Log.v("태그", "드라이버 접근");
                Connection connection = DriverManager.getConnection(url, username, password);
                Log.v("태그", "연결");
                Statement statement = connection.createStatement();

                // add to RDS DB:
                Log.v("태그", "부모 - INSERT INTO 하기 직전");
                statement.execute("INSERT INTO PARENTS (Parents_ID, Parents_PW) VALUES('"+parents_id+"', '"+parents_password+"')");
                Log.v("태그", "부모 - INSERT INTO 완료");
                connection.close();

            }catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Log.v("태그", "왜안돼 시발 이건 addParents catch문입니다.");
            }
        }).start();
    }

    public static void add_parents_child(int child_card_num_toadd, String id_parents){
        new Thread(()->{
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Log.v("태그", "드라이버 접근");
                Connection connection = DriverManager.getConnection(url, username, password);
                Log.v("태그", "연결");
                Statement statement = connection.createStatement();

                Log.v("태그", "부모 아동칼럼 - INSERT INTO 하기 직전");
                statement.execute("UPDATE PARENTS SET Child_1 = "+child_card_num_toadd+" WHERE Parents_ID = "+id_parents);
                Log.v("태그", "부모 아동칼럼 - INSERT INTO 완료");
                connection.close();

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Log.v("태그", "add_parents_child catch문입니다.");
            }
        }).start();
    }

    public void mypage_lack(){
        new Thread(()->{
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();
                // check with RDS DB:
                ResultSet rs = statement.executeQuery("SELECT * FROM EATMEAL WHERE ChildInfo="+cardNum+"AND (convert(varchar(8), write_date, 112) = convert(varchar(8), getdate(), 112))");
                int pointer = 0;
                while(rs.next()){
                    ate_food_eatmeal[pointer] = rs.getString("food_menu");
                    pointer++;
                }
                Log.v("태그","ate_food_meal[0]: "+ate_food_eatmeal[0]);
                Log.v("태그","ate_food_meal 크기?길이?: "+ate_food_eatmeal.length);

            }catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }).start();
    }

}
