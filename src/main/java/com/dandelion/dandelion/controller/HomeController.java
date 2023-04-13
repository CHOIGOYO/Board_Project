package com.dandelion.dandelion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController { // 페이지컨트롤러

    //    기본페이지 반환
    @GetMapping("/")
    public String index(){
        return "main/index"; //=> templates 폴더의 index.html을 찾아감
    }

    @GetMapping("/main")
    public String main(){
        return "main/main";
    }

}
