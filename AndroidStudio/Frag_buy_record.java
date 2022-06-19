package com.example.childmeal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.daum.mf.map.api.MapPOIItem;

import org.checkerframework.checker.units.qual.A;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Frag_buy_record#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Frag_buy_record extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;
    ArrayList<datamodel> dataholder;

    /*
    private static String cardNum;
    String[] ate_food = new String[100];
    *///

    public Frag_buy_record() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Frag_buy_record.
     */
    // TODO: Rename and change types and number of parameters
    public static Frag_buy_record newInstance(String param1, String param2) {
        Frag_buy_record fragment = new Frag_buy_record();
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
        //AWS_RDS aws_rds = new AWS_RDS();
        //Log.v("태그","set_buy_record() 실행함");
        //aws_rds.set_buy_record();

        //aws_rds.set_ate_food(ate_food);
        //Log.v("태그","set_ate_food() 실행함");
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_frag_buy_record, container, false);
        recyclerView = view.findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dataholder = new ArrayList<>();

        /*
        datamodel[] ob = new datamodel[10];
        for(int i=0;i<10;i++) {
            ob[i] = new datamodel();
        }*/

        /*
        try {
            Thread.sleep(3000); // 1000이 1초
        }catch(InterruptedException e){
            System.out.println(e.getMessage()); //sleep 메소드가 발생하는 InterruptedException
        }*/

        /*
        Log.v("태그", "시작"+ate_food[0]);
        for(int i=0; i<100; i++){
            if (ate_food[i]==null){
                break;
            }
            datamodel ob1 = new datamodel(cardNum,ate_food[i]);
            dataholder.add(ob1);
            recyclerView.setAdapter(new myadapter(dataholder));
        }*/
        datamodel ob1 = new datamodel("'111111111111'", "김밥");
        dataholder.add(ob1);

        datamodel ob2 = new datamodel("'111111111111'", "떡볶이");
        dataholder.add(ob2);

        recyclerView.setAdapter(new myadapter(dataholder));
        return view;
    }
}