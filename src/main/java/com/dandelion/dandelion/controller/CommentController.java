package com.dandelion.dandelion.controller;

import com.dandelion.dandelion.dto.CommentsDTO;
import com.dandelion.dandelion.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor //    생성자 주입 방식 사용
@Controller
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;
    // 댓글 저장
    @PostMapping("/save")
    public String commentSave(@RequestBody CommentsDTO commentsDTO) {
        Long boardId = commentService.saveComment(commentsDTO);
        return "redirect:/board/details/" + boardId;
    }
}
