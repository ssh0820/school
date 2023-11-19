package com.zerobase.school.user.service.school;

import com.zerobase.school.user.dto.SchoolDto;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class SchoolOpenAPIService {

    private final SchoolService schoolService;

    public void saveSchoolInfo() throws IOException, ParseException {
        int start = 1;
        int end = 5;
        List<SchoolDto> schoolDtos = new ArrayList<>();

        System.out.println("===SchoolOpenAPIService.getSchoolInfo() is start===");
        //1.get OpenAPI info - 1차
        JSONObject datas= urlConnection(start,end);
        int totalCount = Integer.parseInt(datas.get("list_total_count").toString());
        //2.changeSchoolDtos
        JSONArray dataArr = (JSONArray) datas.get("row");
        for(int i = 0; i < dataArr.size(); i++){
            JSONObject data = (JSONObject) dataArr.get(i);
            SchoolDto dto = schoolService.changeSchoolDtos(data);
            schoolDtos.add(dto);
        }

       for(int i = end+1; i <= totalCount; i++){
            int k = i+5;

           System.out.println("start>"+i);
           System.out.println("end>"+k);

            if(k>totalCount){
                k = totalCount;
                System.out.println("마지막입니다.");
            }

            //1.get OpenAPI info - totalCount/5차
            datas = urlConnection(i, k);
            //2.changeSchoolDtos
            dataArr = (JSONArray) datas.get("row");
            for(int z = 0; z < dataArr.size(); z++){
                JSONObject data = (JSONObject) dataArr.get(z);
                SchoolDto dto = schoolService.changeSchoolDtos(data);
                System.out.println(dto.getName());
                schoolDtos.add(dto);
            }
            i=k;
        }

        //3.insert SchoolInfo;
        schoolService.insertSchcoolInfo(schoolDtos);
        System.out.println("성공적으로 데이터를 가져왔습니다.");

    }

    private JSONObject urlConnection(int num1, int num2) throws IOException, ParseException {
        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
        urlBuilder.append("/" + URLEncoder.encode("497056666a6b696d363271426c6254", "UTF-8")); //인증키
        urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8")); //json
        urlBuilder.append("/" + URLEncoder.encode("neisSchoolInfoJS", "UTF-8")); //서비스명
        urlBuilder.append("/" + URLEncoder.encode(String.valueOf(num1),"UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
        urlBuilder.append("/" + URLEncoder.encode(String.valueOf(num2), "UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/xml");
        System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
        BufferedReader rd;

        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        JSONObject json = (JSONObject) new JSONParser().parse(sb.toString());

        return (JSONObject) json.get("neisSchoolInfoJS");
    }

}
