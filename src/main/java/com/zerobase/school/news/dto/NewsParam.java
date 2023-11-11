package com.zerobase.school.news.dto;

import com.zerobase.school.news.domain.Sort;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

public class NewsParam {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request{
        private String query;
        private int display;
        private int start;
        private Sort sort;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response{
        private DateTime lastBuildDate;
        private int total;
        private int start;
        private int display;
        private String title;
        private String originallink;
        private String link;
        private String description;
        private DateTime pubDate;
    }
}
