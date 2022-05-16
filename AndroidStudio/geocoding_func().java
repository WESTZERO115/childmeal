// ---------- 아동 - 가맹점 지오코딩 함수
    public void geocoding_func() {
        new Thread(()->{
            try{
                // <-------------> AWS RDS - STORELIST 연결 부분입니다.
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();
                // check with RDS DB:
                ResultSet rs = statement.executeQuery("SELECT * FROM STORELIST");
                Log.v("태그", "STORELIST에서 store_address칼럼만 가져옴");

                int pointer = 0;
                while(rs.next()){
                    stores_address[pointer] = rs.getString("store_address");
                    pointer++;
                }

                // <-------------> 지오코딩 부분입니다.
                //인코딩한 String을 넘겨야 원하는 데이터를 받을 수 있다.
                for (int i=0; i<stores_address.length; i++){  //5926 번 반복
                    String address = URLEncoder.encode(stores_address[i], "UTF-8");
                    obj = new URL(GEOCODE_URL+address);

                    HttpURLConnection con = (HttpURLConnection)obj.openConnection();
                    //get으로 받아오면 된다. 자세한 사항은 카카오개발자센터에 나와있다.
                    con.setRequestMethod("GET");
                    con.setRequestProperty("Authorization", GEOCODE_USER_INFO);

                    con.setRequestProperty("content-type", "application/json");
                    con.setDoOutput(true);
                    con.setUseCaches(false);
                    con.setDefaultUseCaches(false);
                    //Log.v("태그", "geo_ setDefaultUseCaches");
                    Charset charset = Charset.forName("UTF-8");
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    //Log.v("태그", "geo_ inputLine 선언");

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    //response 객체를 출력해보자
                    System.out.println(response);
                    Log.v("태그", "geo_ json이전");

                    JSONObject jsonMain = new JSONObject(String.valueOf(response));
                    JSONArray jsonArray = (JSONArray) jsonMain.get("documents");
                    if (jsonArray.length()>0){
                        for(int j=0; j<jsonArray.length(); j++){
                            JSONObject jsonObject = (JSONObject) jsonArray.get(j);
                            x = (String) jsonObject.get("x");
                            y = (String) jsonObject.get("y");
                        }
                    }
                    X[i] = x; // 위도 배열에 x 넣어줌
                    Y[i] = y; // 경도 배열에 y 넣어줌
                }
                Log.v("태그", "X는 "+X[0]);
                Log.v("태그", "X[1]는 "+X[1]);

                for (int num_ = 1; num_< stores_address.length+1; num_++){
                    statement.execute("UPDATE STORELIST SET X = "+ Float.parseFloat(X[num_]) +
                            ", Y = "+Float.parseFloat(Y[num_])+" WHERE store_num="+num_);
                }

                connection.close();

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }).start();


    }
