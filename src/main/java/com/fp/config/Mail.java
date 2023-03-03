package com.fp.config;

import lombok.Data;

import java.util.Map;

@Data
public class Mail {
    private String from;
    private String to;
    private String subject;
    private String content;
    Map<String, Object> model;
}

