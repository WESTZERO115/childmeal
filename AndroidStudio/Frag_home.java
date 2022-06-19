package com.example.childmeal;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Trace;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Frag_home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Frag_home extends Fragment {
    private View view;
    String[] X_frag = new String[5926]; // 위도 배열
    String[] Y_frag = new String[5926]; // 경도 배열
    String[] Store_names = new String[5926];
    int start = 0;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Frag_home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Frag_home.
     */
    // TODO: Rename and change types and number of parameters
    public static Frag_home newInstance(String param1, String param2) {

        Frag_home fragment = new Frag_home();
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

    public void setstart(){
        Log.v("태그", "setStart 함수 실행되었음");
        this.start = 1;
        Log.v("태그", "start : "+ start);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.v("태그", "marker함수 실행할 예정");
        AWS_RDS aws = new AWS_RDS();
        aws.marker();

        view = inflater.inflate(R.layout.fragment_frag_home,container,false);

        //지도
        MapView mapView = new MapView(getActivity());
        ViewGroup mapViewContainer = (ViewGroup) view.findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);
        // 중심점 변경 - 예제 좌표는 남양주시청
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.6493611, 127.302528), true);
        // // 줌 레벨 변경
        mapView.setZoomLevel(4, true);
        Log.v("태그", "현위치 마커 찍기 전");

        //마커 찍기
        MapPoint MARKER_POINT = MapPoint.mapPointWithGeoCoord(37.6493611, 127.302528);
        MapPOIItem marker = new MapPOIItem();
        marker.setItemName("현 위치");
        marker.setTag(0);
        marker.setMapPoint(MARKER_POINT);
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.BluePin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker);


        try {
            Thread.sleep(10000); // 1000이 1초
        }catch(InterruptedException e){
            System.out.println(e.getMessage()); //sleep 메소드가 발생하는 InterruptedException
        }


        aws.setX(X_frag);
        aws.setY(Y_frag);
        aws.set_store_name(Store_names);

        Log.v("태그", "X_frag는 "+Float.parseFloat(X_frag[0]));
        Log.v("태그", "X_frag[1]는 "+Float.parseFloat(X_frag[1]));
        Log.v("태그", "X_frag[99]는 "+Float.parseFloat(X_frag[99]));
        Log.v("태그", "Y_frag는 "+Float.parseFloat(Y_frag[0]));


        MapPOIItem[] stores_marker = new MapPOIItem[5926];
        for(int i=0;i<stores_marker.length;i++) {
            stores_marker[i] = new MapPOIItem();
        }
        for (int i=0; i<100; i++) {
            MapPoint marker_points = MapPoint.mapPointWithGeoCoord(Float.parseFloat(Y_frag[i]), Float.parseFloat(X_frag[i]));
            stores_marker[i].setItemName(Store_names[i]);
            stores_marker[i].setTag(i + 1); // 0은 현위치, 가맹점은 1~5926.
            stores_marker[i].setMapPoint(marker_points);
            stores_marker[i].setMarkerType(MapPOIItem.MarkerType.RedPin);
            stores_marker[i].setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
            mapView.addPOIItem(stores_marker[i]);
        }

        MapPoint MARKER_POINT2 = MapPoint.mapPointWithGeoCoord(37.6085, 127.152);
        MapPOIItem marker2 = new MapPOIItem();
        marker2.setItemName("갓지은솥밥");
        marker2.setTag(101);
        marker2.setMapPoint(MARKER_POINT2);
        marker2.setMarkerType(MapPOIItem.MarkerType.RedPin); // 기본으로 제공하는 BluePin 마커 모양.
        marker2.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker2);

        MapPoint MARKER_POINT3 = MapPoint.mapPointWithGeoCoord(37.5867, 127.213);
        MapPOIItem marker3 = new MapPOIItem();
        marker3.setItemName("강남식당");
        marker3.setTag(102);
        marker3.setMapPoint(MARKER_POINT3);
        marker3.setMarkerType(MapPOIItem.MarkerType.RedPin); // 기본으로 제공하는 BluePin 마커 모양.
        marker3.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker3);

        MapPoint MARKER_POINT4 = MapPoint.mapPointWithGeoCoord(37.6843, 127.212);
        MapPOIItem marker4 = new MapPOIItem();
        marker4.setItemName("곽만근갈비탕");
        marker4.setTag(103);
        marker4.setMapPoint(MARKER_POINT4);
        marker4.setMarkerType(MapPOIItem.MarkerType.RedPin); // 기본으로 제공하는 BluePin 마커 모양.
        marker4.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker4);

        MapPoint MARKER_POINT5 = MapPoint.mapPointWithGeoCoord(37.718, 127.183);
        MapPOIItem marker5 = new MapPOIItem();
        marker5.setItemName("경회루");
        marker5.setTag(104);
        marker5.setMapPoint(MARKER_POINT5);
        marker5.setMarkerType(MapPOIItem.MarkerType.RedPin); // 기본으로 제공하는 BluePin 마커 모양.
        marker5.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker5);

        MapPoint MARKER_POINT6 = MapPoint.mapPointWithGeoCoord(37.7288, 127.195);
        MapPOIItem marker6 = new MapPOIItem();
        marker6.setItemName("김밥천국(마석1호점)");
        marker6.setTag(105);
        marker6.setMapPoint(MARKER_POINT6);
        marker6.setMarkerType(MapPOIItem.MarkerType.RedPin); // 기본으로 제공하는 BluePin 마커 모양.
        marker6.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker6);

        MapPoint MARKER_POINT7 = MapPoint.mapPointWithGeoCoord(37.6213, 127.159);
        MapPOIItem marker7 = new MapPOIItem();
        marker7.setItemName("깡돈");
        marker7.setTag(106);
        marker7.setMapPoint(MARKER_POINT7);
        marker7.setMarkerType(MapPOIItem.MarkerType.RedPin); // 기본으로 제공하는 BluePin 마커 모양.
        marker7.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker7);

        MapPoint MARKER_POINT8 = MapPoint.mapPointWithGeoCoord(37.7182, 127.202);
        MapPOIItem marker8 = new MapPOIItem();
        marker8.setItemName("네네치킨");
        marker8.setTag(107);
        marker8.setMapPoint(MARKER_POINT8);
        marker8.setMarkerType(MapPOIItem.MarkerType.RedPin); // 기본으로 제공하는 BluePin 마커 모양.
        marker8.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker8);

        MapPoint MARKER_POINT9 = MapPoint.mapPointWithGeoCoord(37.6384, 127.304);
        MapPOIItem marker9 = new MapPOIItem();
        marker9.setItemName("더꼬치다");
        marker9.setTag(108);
        marker9.setMapPoint(MARKER_POINT9);
        marker9.setMarkerType(MapPOIItem.MarkerType.RedPin); // 기본으로 제공하는 BluePin 마커 모양.
        marker9.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker9);

        MapPoint MARKER_POINT10 = MapPoint.mapPointWithGeoCoord(37.646, 127.234);
        MapPOIItem marker10 = new MapPOIItem();
        marker10.setItemName("더담부대찌개");
        marker10.setTag(109);
        marker10.setMapPoint(MARKER_POINT10);
        marker10.setMarkerType(MapPOIItem.MarkerType.RedPin); // 기본으로 제공하는 BluePin 마커 모양.
        marker10.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker10);


        MapPoint MARKER_POINT11 = MapPoint.mapPointWithGeoCoord(37.6545, 127.245);
        MapPOIItem marker11 = new MapPOIItem();
        marker11.setItemName("덕이네감자탕");
        marker11.setTag(110);
        marker11.setMapPoint(MARKER_POINT11);
        marker11.setMarkerType(MapPOIItem.MarkerType.RedPin); // 기본으로 제공하는 BluePin 마커 모양.
        marker11.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker11);


        MapPoint MARKER_POINT12 = MapPoint.mapPointWithGeoCoord(37.609, 127.164);
        MapPOIItem marker12 = new MapPOIItem();
        marker12.setItemName("떡볶이참잘하는집떡참화도점");
        marker12.setTag(111);
        marker12.setMapPoint(MARKER_POINT12);
        marker12.setMarkerType(MapPOIItem.MarkerType.RedPin); // 기본으로 제공하는 BluePin 마커 모양.
        marker12.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker12);

        MapPoint MARKER_POINT13 = MapPoint.mapPointWithGeoCoord(37.6583, 127.298);
        MapPOIItem marker13 = new MapPOIItem();
        marker13.setItemName("뚱스땡스");
        marker13.setTag(112);
        marker13.setMapPoint(MARKER_POINT13);
        marker13.setMarkerType(MapPOIItem.MarkerType.RedPin); // 기본으로 제공하는 BluePin 마커 모양.
        marker13.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker13);

        //////////
        MapPoint MARKER_POINT14 = MapPoint.mapPointWithGeoCoord(37.6499, 127.304);
        MapPOIItem marker14 = new MapPOIItem();
        marker14.setItemName("봉동칼국수");
        marker14.setTag(113);
        marker14.setMapPoint(MARKER_POINT14);
        marker14.setMarkerType(MapPOIItem.MarkerType.RedPin); // 기본으로 제공하는 BluePin 마커 모양.
        marker14.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker14);

        MapPoint MARKER_POINT15 = MapPoint.mapPointWithGeoCoord(37.6503, 127.304);
        MapPOIItem marker15 = new MapPOIItem();
        marker15.setItemName("매콩이 달콩이");
        marker15.setTag(114);
        marker15.setMapPoint(MARKER_POINT15);
        marker15.setMarkerType(MapPOIItem.MarkerType.RedPin); // 기본으로 제공하는 BluePin 마커 모양.
        marker15.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker15);

        MapPoint MARKER_POINT16 = MapPoint.mapPointWithGeoCoord(37.6504, 127.303);
        MapPOIItem marker16 = new MapPOIItem();
        marker16.setItemName("짬봉의신 마석점");
        marker16.setTag(115);
        marker16.setMapPoint(MARKER_POINT16);
        marker16.setMarkerType(MapPOIItem.MarkerType.RedPin); // 기본으로 제공하는 BluePin 마커 모양.
        marker16.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker16);

        MapPoint MARKER_POINT17 = MapPoint.mapPointWithGeoCoord(37.6502, 127.302);
        MapPOIItem marker17 = new MapPOIItem();
        marker17.setItemName("삼첩분식 마석점");
        marker17.setTag(116);
        marker17.setMapPoint(MARKER_POINT17);
        marker17.setMarkerType(MapPOIItem.MarkerType.RedPin); // 기본으로 제공하는 BluePin 마커 모양.
        marker17.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker17);

        MapPoint MARKER_POINT18 = MapPoint.mapPointWithGeoCoord(37.6507, 127.302);
        MapPOIItem marker18 = new MapPOIItem();
        marker18.setItemName("타이양");
        marker18.setTag(117);
        marker18.setMapPoint(MARKER_POINT18);
        marker18.setMarkerType(MapPOIItem.MarkerType.RedPin); // 기본으로 제공하는 BluePin 마커 모양.
        marker18.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker18);

        MapPoint MARKER_POINT19 = MapPoint.mapPointWithGeoCoord(37.65, 127.304);
        MapPOIItem marker19 = new MapPOIItem();
        marker19.setItemName("김밥천국 마석1호점");
        marker19.setTag(118);
        marker19.setMapPoint(MARKER_POINT19);
        marker19.setMarkerType(MapPOIItem.MarkerType.RedPin); // 기본으로 제공하는 BluePin 마커 모양.
        marker19.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker19);

        MapPoint MARKER_POINT20 = MapPoint.mapPointWithGeoCoord(37.6515, 127.304);
        MapPOIItem marker20 = new MapPOIItem();
        marker20.setItemName("본도시락 남양주마석");
        marker20.setTag(119);
        marker20.setMapPoint(MARKER_POINT20);
        marker20.setMarkerType(MapPOIItem.MarkerType.RedPin); // 기본으로 제공하는 BluePin 마커 모양.
        marker20.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker20);

        MapPoint MARKER_POINT21 = MapPoint.mapPointWithGeoCoord(37.6517, 127.304);
        MapPOIItem marker21 = new MapPOIItem();
        marker21.setItemName("굴다리 전주 콩나물국밥 마석점");
        marker21.setTag(120);
        marker21.setMapPoint(MARKER_POINT21);
        marker21.setMarkerType(MapPOIItem.MarkerType.RedPin); // 기본으로 제공하는 BluePin 마커 모양.
        marker21.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker21);

        MapPoint MARKER_POINT22 = MapPoint.mapPointWithGeoCoord(37.6517, 127.304);
        MapPOIItem marker22 = new MapPOIItem();
        marker22.setItemName("차오마미엔");
        marker22.setTag(121);
        marker22.setMapPoint(MARKER_POINT22);
        marker22.setMarkerType(MapPOIItem.MarkerType.RedPin); // 기본으로 제공하는 BluePin 마커 모양.
        marker22.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker22);

        MapPoint MARKER_POINT23 = MapPoint.mapPointWithGeoCoord(37.6466, 127.304);
        MapPOIItem marker23 = new MapPOIItem();
        marker23.setItemName("봉구스밥버거 창현점");
        marker23.setTag(122);
        marker23.setMapPoint(MARKER_POINT23);
        marker23.setMarkerType(MapPOIItem.MarkerType.RedPin); // 기본으로 제공하는 BluePin 마커 모양.
        marker23.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker23);

        MapPoint MARKER_POINT24 = MapPoint.mapPointWithGeoCoord(37.6523, 127.304);
        MapPOIItem marker24 = new MapPOIItem();
        marker24.setItemName("코끼리 손만두");
        marker24.setTag(123);
        marker24.setMapPoint(MARKER_POINT24);
        marker24.setMarkerType(MapPOIItem.MarkerType.RedPin); // 기본으로 제공하는 BluePin 마커 모양.
        marker24.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker24);

        MapPoint MARKER_POINT25 = MapPoint.mapPointWithGeoCoord(37.6495, 127.306);
        MapPOIItem marker25 = new MapPOIItem();
        marker25.setItemName("수유리우동집 화도점");
        marker25.setTag(124);
        marker25.setMapPoint(MARKER_POINT25);
        marker25.setMarkerType(MapPOIItem.MarkerType.RedPin); // 기본으로 제공하는 BluePin 마커 모양.
        marker25.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker25);

        MapPoint MARKER_POINT26 = MapPoint.mapPointWithGeoCoord(37.6521, 127.307);
        MapPOIItem marker26 = new MapPOIItem();
        marker26.setItemName("올레국수집");
        marker26.setTag(125);
        marker26.setMapPoint(MARKER_POINT26);
        marker26.setMarkerType(MapPOIItem.MarkerType.RedPin); // 기본으로 제공하는 BluePin 마커 모양.
        marker26.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker26);

        MapPoint MARKER_POINT27 = MapPoint.mapPointWithGeoCoord(37.6515, 127.306);
        MapPOIItem marker27 = new MapPOIItem();
        marker27.setItemName("요녀석 화도점");
        marker27.setTag(126);
        marker27.setMapPoint(MARKER_POINT27);
        marker27.setMarkerType(MapPOIItem.MarkerType.RedPin); // 기본으로 제공하는 BluePin 마커 모양.
        marker27.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker27);

        MapPoint MARKER_POINT28 = MapPoint.mapPointWithGeoCoord(37.65, 127.304);
        MapPOIItem marker28 = new MapPOIItem();
        marker28.setItemName("착한파스타 창현점");
        marker28.setTag(127);
        marker28.setMapPoint(MARKER_POINT28);
        marker28.setMarkerType(MapPOIItem.MarkerType.RedPin); // 기본으로 제공하는 BluePin 마커 모양.
        marker28.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker28);


        MapPoint MARKER_POINT29 = MapPoint.mapPointWithGeoCoord(37.6462, 127.305);
        MapPOIItem marker29 = new MapPOIItem();
        marker29.setItemName("혜화동돈까스극장 남");
        marker29.setTag(128);
        marker29.setMapPoint(MARKER_POINT29);
        marker29.setMarkerType(MapPOIItem.MarkerType.RedPin); // 기본으로 제공하는 BluePin 마커 모양.
        marker29.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker29);

        MapPoint MARKER_POINT30 = MapPoint.mapPointWithGeoCoord(37.652, 127.307);
        MapPOIItem marker30 = new MapPOIItem();
        marker30.setItemName("핵밥");
        marker30.setTag(129);
        marker30.setMapPoint(MARKER_POINT30);
        marker30.setMarkerType(MapPOIItem.MarkerType.RedPin); // 기본으로 제공하는 BluePin 마커 모양.
        marker30.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker30);

        MapPoint MARKER_POINT31 = MapPoint.mapPointWithGeoCoord(37.6495, 127.306);
        MapPOIItem marker31 = new MapPOIItem();
        marker31.setItemName("김밥천국 창현점");
        marker31.setTag(130);
        marker31.setMapPoint(MARKER_POINT31);
        marker31.setMarkerType(MapPOIItem.MarkerType.RedPin); // 기본으로 제공하는 BluePin 마커 모양.
        marker31.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker31);

        MapPoint MARKER_POINT32 = MapPoint.mapPointWithGeoCoord(37.6471, 127.306);
        MapPOIItem marker32 = new MapPOIItem();
        marker32.setItemName("마미맘피자 마석점");
        marker32.setTag(131);
        marker32.setMapPoint(MARKER_POINT32);
        marker32.setMarkerType(MapPOIItem.MarkerType.RedPin); // 기본으로 제공하는 BluePin 마커 모양.
        marker32.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker32);

        MapPoint MARKER_POINT33 = MapPoint.mapPointWithGeoCoord(37.6471, 127.306);
        MapPOIItem marker33 = new MapPOIItem();
        marker33.setItemName("롤포켓");
        marker33.setTag(132);
        marker33.setMapPoint(MARKER_POINT33);
        marker33.setMarkerType(MapPOIItem.MarkerType.RedPin); // 기본으로 제공하는 BluePin 마커 모양.
        marker33.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker33);

        MapPoint MARKER_POINT34 = MapPoint.mapPointWithGeoCoord(37.6507, 127.306);
        MapPOIItem marker34 = new MapPOIItem();
        marker34.setItemName("굴다리식당");
        marker34.setTag(133);
        marker34.setMapPoint(MARKER_POINT34);
        marker34.setMarkerType(MapPOIItem.MarkerType.RedPin); // 기본으로 제공하는 BluePin 마커 모양.
        marker34.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker34);

        MapPoint MARKER_POINT35 = MapPoint.mapPointWithGeoCoord(37.6495, 127.306);
        MapPOIItem marker35 = new MapPOIItem();
        marker35.setItemName("뜨락보리밥");
        marker35.setTag(134);
        marker35.setMapPoint(MARKER_POINT35);
        marker35.setMarkerType(MapPOIItem.MarkerType.RedPin); // 기본으로 제공하는 BluePin 마커 모양.
        marker35.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker35);

        MapPoint MARKER_POINT36 = MapPoint.mapPointWithGeoCoord(37.6505, 127.304);
        MapPOIItem marker36 = new MapPOIItem();
        marker36.setItemName("명동칼국수 창현점");
        marker36.setTag(135);
        marker36.setMapPoint(MARKER_POINT36);
        marker36.setMarkerType(MapPOIItem.MarkerType.RedPin); // 기본으로 제공하는 BluePin 마커 모양.
        marker36.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker36);

        MapPoint MARKER_POINT37 = MapPoint.mapPointWithGeoCoord(37.653, 127.303);
        MapPOIItem marker37 = new MapPOIItem();
        marker37.setItemName("소풍가는날");
        marker37.setTag(136);
        marker37.setMapPoint(MARKER_POINT37);
        marker37.setMarkerType(MapPOIItem.MarkerType.RedPin); // 기본으로 제공하는 BluePin 마커 모양.
        marker37.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker37);

        MapPoint MARKER_POINT38 = MapPoint.mapPointWithGeoCoord(37.6525, 127.302);
        MapPOIItem marker38 = new MapPOIItem();
        marker38.setItemName("계놈 프로젝트 마석");
        marker38.setTag(137);
        marker38.setMapPoint(MARKER_POINT38);
        marker38.setMarkerType(MapPOIItem.MarkerType.RedPin); // 기본으로 제공하는 BluePin 마커 모양.
        marker38.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker38);

        MapPoint MARKER_POINT39 = MapPoint.mapPointWithGeoCoord(37.6504, 127.303);
        MapPOIItem marker39 = new MapPOIItem();
        marker39.setItemName("챙길 밥 화도점");
        marker39.setTag(137);
        marker39.setMapPoint(MARKER_POINT39);
        marker39.setMarkerType(MapPOIItem.MarkerType.RedPin); // 기본으로 제공하는 BluePin 마커 모양.
        marker39.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker39);
        //return inflater.inflate(R.layout.fragment_frag_home, container, false);
        return view;
    }

}