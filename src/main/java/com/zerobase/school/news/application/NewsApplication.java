package com.zerobase.school.news.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerobase.school.news.dto.NewsParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsApplication {
   /*

    curl "https://openapi.naver.com/v1/search/news.json?query=%EC%A3%BC%EC%8B%9D&display=10&start=1&sort=sim" \
    -H "X-Naver-Client-Id: {애플리케이션 등록 시 발급받은 클라이언트 아이디 값}" \
    -H "X-Naver-Client-Secret: {애플리케이션 등록 시 발급받은 클라이언트 시크릿 값}" -v

     */

    public List<NewsParam.Response> getNewsJson(NewsParam.Request request) throws IOException {

        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com/")
                .path("/v1/search/news.json")
                .queryParam("query", request.getQuery())
                .queryParam("display", request.getDisplay())
                .queryParam("start", request.getStart())
                .queryParam("sort", request.getSort())
                .encode()
                .build()
                .toUri();

        RequestEntity<Void> req = RequestEntity.get(uri)
                .header("X-Naver-Client-Id", "")
                .header("X-Naver-Client-Secret", "")
                .build();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(req, String.class);

        // JSON 파싱 (Json 문자열을 객체로 만듦, 문서화)
        ObjectMapper om = new ObjectMapper();
        NewsParam.Response news = null;

        try {
            news = om.readValue(resp.getBody(), NewsParam.Response.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        List<NewsParam.Response> newsList = news.getNewsDtoList();

        return newsList;
    }
}
