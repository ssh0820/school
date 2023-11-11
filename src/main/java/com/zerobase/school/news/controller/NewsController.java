package com.zerobase.school.news.controller;

import com.zerobase.school.news.application.NewsApplication;
import com.zerobase.school.news.dto.NewsParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsApplication newsApplication;

    @GetMapping("/list")
    public List<NewsParam.Response> getNewsList(NewsParam.Request request) throws IOException {
        return newsApplication.getNewsJson(request);
    }

}
