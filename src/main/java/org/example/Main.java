package org.example;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;


import org.json.simple.JSONObject;

public class Main {
    public static void main(String[] args) {

        FileInputStream fis = null;

        try {
            for(String element : System.getProperty("java.class.path").split(":")) {
                System.out.println(element);
            }
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            String nam=loader.getName();
            InputStream is = loader.getResourceAsStream("globlas.properties");
            Properties props = new Properties();
            props.load(is);
            String calsApiUrl =props.getProperty("calsApiUrl");
            URL url = new URL(calsApiUrl);
            //URL url = new URL("http://rcicwasIP:추후정의(PORT)/api/calsdata");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();

            conn.setRequestMethod("POST"); // http 메서드
            conn.setRequestProperty("Content-Type", "application/json"); // header Content-Type 정보
            conn.setDoInput(true); // 서버에 전달할 값이 있다면 true
            conn.setDoOutput(true); // 서버로부터 받는 값이 있다면 true

            //rcic 서버에 건설cals 데이터리스트 "dataList"라는 키에 담아 json 형식으로 전달
            StringBuffer calsdata = new StringBuffer();

            calsdata.append("{");
            calsdata.append("\"dataList\":");
            calsdata.append( "[");

            calsdata.append("{\"PRMSN_NO\":\"광명허가1\",\"OCPY_POS\":\"단독주택 진출입로1\",\"OCPY_LOC\":\"강릉시 연곡면 삼산리 654-3\",\"OCPY_DUR\":\"20231229 \\/ 20321231\",\"WORK_EN_DT\":null,\"REQ_DT\":\"20240109\",\"ROAD_TYPE\":\"일반국도\",\"PRMSN_DT\":\"20231229\",\"INFO_NO\":\"9\",\"REQ_TYPE\":\"1\",\"STATE_CD\":\"1\",\"OCPY_AREA\":\"13㎡\",\"MNGR_NM\":\"강릉국토관리사무소\",\"OCPY_PER_INFO\":\"강원특별자치도 강릉시 영진3길 15 부영사랑으로 아파트 \\/ 전덕우\",\"ROAD_NM\":\"6호선\",\"WORK_ST_DT\":null},");
            calsdata.append("{\"PRMSN_NO\":\"광명허가2\",\"OCPY_POS\":\"단독주택 진출입로1\",\"OCPY_LOC\":\"강릉시 연곡면 삼산리 654-3\",\"OCPY_DUR\":\"20231229 \\/ 20321231\",\"WORK_EN_DT\":null,\"REQ_DT\":\"20240109\",\"ROAD_TYPE\":\"일반국도\",\"PRMSN_DT\":\"20231229\",\"INFO_NO\":\"9\",\"REQ_TYPE\":\"1\",\"STATE_CD\":\"1\",\"OCPY_AREA\":\"13㎡\",\"MNGR_NM\":\"강릉국토관리사무소\",\"OCPY_PER_INFO\":\"강원특별자치도 강릉시 영진3길 15 부영사랑으로 아파트 \\/ 전덕우\",\"ROAD_NM\":\"6호선\",\"WORK_ST_DT\":null},");
            calsdata.append("{\"PRMSN_NO\":\"광명허가3\",\"OCPY_POS\":\"단독주택 진출입로3\",\"OCPY_LOC\":\"강릉시 연곡면 삼산리 654-3\",\"OCPY_DUR\":\"20231229 \\/ 20321231\",\"WORK_EN_DT\":null,\"REQ_DT\":\"20240109\",\"ROAD_TYPE\":\"일반국도\",\"PRMSN_DT\":\"20231229\",\"INFO_NO\":\"9\",\"REQ_TYPE\":\"1\",\"STATE_CD\":\"1\",\"OCPY_AREA\":\"13㎡\",\"MNGR_NM\":\"강릉국토관리사무소\",\"OCPY_PER_INFO\":\"강원특별자치도 강릉시 영진3길 15 부영사랑으로 아파트 \\/ 전덕우\",\"ROAD_NM\":\"6호선\",\"WORK_ST_DT\":null},");
            calsdata.append("{\"PRMSN_NO\":\"광명허가4\",\"OCPY_POS\":\"단독주택 진출입로4\",\"OCPY_LOC\":\"강릉시 연곡면 삼산리 654-3\",\"OCPY_DUR\":\"20231229 \\/ 20321231\",\"WORK_EN_DT\":null,\"REQ_DT\":\"20240109\",\"ROAD_TYPE\":\"일반국도\",\"PRMSN_DT\":\"20231229\",\"INFO_NO\":\"9\",\"REQ_TYPE\":\"1\",\"STATE_CD\":\"1\",\"OCPY_AREA\":\"13㎡\",\"MNGR_NM\":\"강릉국토관리사무소\",\"OCPY_PER_INFO\":\"강원특별자치도 강릉시 영진3길 15 부영사랑으로 아파트 \\/ 전덕우\",\"ROAD_NM\":\"6호선\",\"WORK_ST_DT\":null},");




            calsdata.append("]");
            calsdata.append("}");

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
            bw.write(calsdata.toString()); // 버퍼에 담기
            bw.flush(); // 버퍼에 담긴 데이터 전달
            bw.close();

            // 서버로부터 데이터 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;

            while((line = br.readLine()) != null) { // 읽을 수 있을 때 까지 반복
                sb.append(line);
            }

            System.out.println(sb.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}