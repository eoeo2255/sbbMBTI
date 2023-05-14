package com.ll.mbti;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @ResponseBody
    @GetMapping("/mbti")    //  url 과 매핑된 함수는 결과값을 리턴해야 한다.
    public String index() {
        return "What's your MBTI?";
    }

    @GetMapping("/")
    public String root() {
        return "redirect: /article/list";
    }

}
