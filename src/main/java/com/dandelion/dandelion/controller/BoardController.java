package com.dandelion.dandelion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
    @GetMapping("/infolist")
    public String infolist(){
        return "views/infoBoardList";
    }
    @GetMapping("/frontlist")
    public String frontlist(){
        return "views/frontBoardList";
    }
    @GetMapping("/backlist")
    public String backlist(){
        return "views/backBoardList";
    }
}
