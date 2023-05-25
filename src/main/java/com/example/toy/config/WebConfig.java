package com.example.toy.config;


import com.example.toy.common.BoardToViewBoardConverter;
import com.example.toy.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final MemberService memberService;

    @Autowired
    public WebConfig(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new BoardToViewBoardConverter(memberService));
    }
}
