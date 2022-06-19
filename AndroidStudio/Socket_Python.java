package com.example.childmeal;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class Socket_Python extends AppCompatActivity {

    /*
    private Handler mHandler;
    private java.net.Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;
    private String ip = "15.164.212.222";
    private int port = 22;
    private String img_path; */

    private String html = "";
    private Handler mHandler;
    private Socket socket;

    private DataOutputStream dos;
    private DataInputStream dis;

    private String ip = "3.34.253.125";            // IP 번호
    private int port = 5555;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // 로그인 정보 db에 넣어주고 연결시켜야 함.
    void connect(){
        mHandler = new Handler();
        Log.w("태그","연결 하는중");
        // 받아오는거
        Thread checkUpdate = new Thread() {
            public void run() {
                // ip받기
                //String newip = String.valueOf(ip_edit.getText());

                // 서버 접속
                try {
                    socket = new Socket(ip, port);
                    Log.w("태그", "서버 접속됨");
                } catch (IOException e1) {
                    Log.w("태그", "서버접속못함");
                    e1.printStackTrace();
                }

                Log.w("태그","edit 넘어가야 할 값 : 안드로이드에서 서버로 연결요청");

                try {
                    dos = new DataOutputStream(socket.getOutputStream());   // output에 보낼꺼 넣음
                    dis = new DataInputStream(socket.getInputStream());     // input에 받을꺼 넣어짐
                    dos.writeUTF("안드로이드에서 서버로 연결요청");

                } catch (IOException e) {
                    e.printStackTrace();
                    Log.w("태그", "버퍼생성 잘못됨");
                }
                Log.w("태그","버퍼생성 잘됨");

                // 서버에서 계속 받아옴 - 한번은 문자, 한번은 숫자를 읽음. 순서 맞춰줘야 함.
                try {

                    //Float line;
                    int line2;
                    while(true) {
                        //line = (String)dis.readUTF();
                        line2 = (int)dis.read();


                    }
                }catch (Exception e){
                }
            }
        };
        // 소켓 접속 시도, 버퍼생성
        checkUpdate.start();
    }
}

