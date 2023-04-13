package com.dandelion.dandelion.controller;

import com.dandelion.dandelion.dto.BoardDTO;
import com.dandelion.dandelion.entity.BoardEntity;
import com.dandelion.dandelion.entity.MemberEntity;
import com.dandelion.dandelion.repository.BoardRepository;
import com.dandelion.dandelion.repository.MemberRepository;
import com.dandelion.dandelion.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor //    생성자 주입 방식 사용
@Controller
@RequestMapping("/board")
public class BoardController {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final BoardService boardService;

    @GetMapping("/infolist")
    public String infolist(Model model){
        String category = "infoBoard";
        List<BoardEntity> boardList = boardService.getBoardsByCategory(category);
        model.addAttribute("boardList", boardList);
        return "views/infoBoardList";
    }
    @GetMapping("/frontlist")
    public String frontlist(Model model){
        String category = "frontBoard";
        List<BoardEntity> boardList = boardService.getBoardsByCategory(category);
        model.addAttribute("boardList", boardList);
        return "views/frontBoardList";
    }
    @GetMapping("/backlist")
    public String backlist(Model model){
        String category = "backBoard";
        List<BoardEntity> boardList = boardService.getBoardsByCategory(category);
        model.addAttribute("boardList", boardList);
        return "views/backBoardList";
    }

    @GetMapping("/writeForm")
    public String writeForm(){
        return "views/writeForm";
    }

    @PostMapping("/save")
    public String boardSave(@ModelAttribute BoardDTO boardDTO, HttpSession httpSession) {
        String nonce = ""; // 임시변수 선언
        Long memberId = (Long) httpSession.getAttribute("id"); // 회원번호를 id라는 name에 저장했음 주의
        // 현재 로그인한 사용자의 MemberEntity 가져오기
        System.out.println("세션에 저장된 id값 =>"+memberId);
        Optional<MemberEntity> memberOpt = memberRepository.findById(memberId);
        MemberEntity member = memberOpt.orElse(null);
        System.out.println("boardDTO : " + boardDTO);
        // 게시글 저장
        BoardEntity boardEntity = boardService.toBoardEntity(boardDTO, member);
        nonce = boardDTO.getCategories(); // 저장하기 전 임시변수에 현 카테고리 저장
        boardRepository.save(boardEntity);

        // 저장한 카테고리의 글 리스트를 반환하도록
        switch (nonce) {
            case "infoBoard":
                return "redirect:/board/infolist";
            case "frontBoard":
                return "redirect:/board/frontlist";
            case "backBoard":
                return "redirect:/board/backlist";
            default:
                return "redirect:/"; // 기본 경로로 리디렉션
        }
    }
}
