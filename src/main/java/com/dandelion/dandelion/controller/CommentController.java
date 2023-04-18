package com.dandelion.dandelion.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor //    생성자 주입 방식 사용
@Controller
@RequestMapping("/comment")
public class CommentController {
}
