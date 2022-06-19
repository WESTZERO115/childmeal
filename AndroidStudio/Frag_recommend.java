package com.example.childmeal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Frag_recommend#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Frag_recommend extends Fragment {
    TextView tv_Food;
    TextView tv_Store;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Frag_recommend() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Frag_recommend.
     */
    // TODO: Rename and change types and number of parameters
    public static Frag_recommend newInstance(String param1, String param2) {
        Frag_recommend fragment = new Frag_recommend();
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

        View view = inflater.inflate(R.layout.fragment_frag_recommend, container, false);
        tv_Food = (TextView) view.findViewById(R.id.tv_food);
        tv_Store = (TextView) view.findViewById(R.id.tv_store);

        tv_Food.setText("갈비탕");
        tv_Store.setText("곽만근갈비탕에서");
        // Inflate the layout for this fragment

        //return inflater.inflate(R.layout.fragment_frag_recommend, container, false);
        return view;
    }
}