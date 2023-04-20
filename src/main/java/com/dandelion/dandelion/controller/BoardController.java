package com.dandelion.dandelion.controller;

import com.dandelion.dandelion.dto.BoardDTO;
import com.dandelion.dandelion.dto.CommentsDTO;
import com.dandelion.dandelion.entity.BoardEntity;
import com.dandelion.dandelion.entity.MemberEntity;
import com.dandelion.dandelion.repository.BoardRepository;
import com.dandelion.dandelion.repository.MemberRepository;
import com.dandelion.dandelion.service.BoardService;
import com.dandelion.dandelion.service.CommentService;
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
    private final CommentService commentService;

    // 인포리스트 반환
    @GetMapping("/infolist")
    public String infolist(Model model){
        String category = "infoBoard";
        List<BoardEntity> boardList = boardService.getBoardsByCategory(category);
        model.addAttribute("boardList", boardList);
        return "views/infoBoardList";
    }
    // 프론트엔드 리스트
    @GetMapping("/frontlist")
    public String frontlist(Model model){
        String category = "frontBoard";
        List<BoardEntity> boardList = boardService.getBoardsByCategory(category);
        model.addAttribute("boardList", boardList);
        return "views/frontBoardList";
    }
    // 백엔드 리스트
    @GetMapping("/backlist")
    public String backlist(Model model){
        String category = "backBoard";
        List<BoardEntity> boardList = boardService.getBoardsByCategory(category);
        model.addAttribute("boardList", boardList);
        return "views/backBoardList";
    }

    // 글쓰기 폼
    @GetMapping("/writeForm")
    public String writeForm(){
        return "views/writeForm";
    }

    // 게시글 저장 
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
    // 게시글 상세보기
    @GetMapping("/details/{id}")
    public String boardDetails(@PathVariable("id") Long id, Model model, HttpSession httpSession){
        //세션에서 사용자 ID와 역할 가져오기
        Long userId = (Long) httpSession.getAttribute("id");
        String userRole = (String) httpSession.getAttribute("role");
        //사용자의 ID와 역할을  model에 추가
        model.addAttribute("userId", userId);
        model.addAttribute("userRole", userRole);


        Optional<BoardEntity> boardOpt = boardRepository.findById(id);
        if (boardOpt.isPresent()) {
            BoardEntity boardEntity = boardOpt.get();
            BoardDTO boardDTO = boardService.toBoardDTO(boardEntity);
            model.addAttribute("board", boardDTO);

            List<CommentsDTO> comments = commentService.getCommentsByBoardId(id);
            model.addAttribute("comments", comments); // 댓글 목록 추가
            return "views/boardDetails";
        } else {
            System.out.println("게시글이 존재하지 않는 경우");
            return "redirect:/previousPage"; // 게시글이 없는 경우 이전 페이지로 리디렉션
        }
    }
    
    // 게시글 수정페이지 반환
    @GetMapping("/update/{id}")
    public String updateBoard(@PathVariable("id") Long id, Model model) {
        Optional<BoardEntity> boardOpt = boardRepository.findById(id);
        if (boardOpt.isPresent()) {
            BoardEntity boardEntity = boardOpt.get();
            BoardDTO boardDTO = boardService.toBoardDTO(boardEntity);
            model.addAttribute("board", boardDTO);
            return "views/boardUpdate";
        } else {
            System.out.println("게시글 수정 실패");
            return "redirect:/previousPage"; // 게시글이 없는 경우 이전 페이지로 리디렉션
        }
    }
    // 게시글 수정 후 업데이트
    @PostMapping("/editBoardSave/{id}")
    public String saveEditedBoard(@PathVariable("id") Long id, @ModelAttribute("board") BoardDTO boardDTO) {
        boardService.updateBoard(id, boardDTO);
        return "redirect:/board/update/{id}";
    }

    // 게시글 삭제
    @GetMapping("/delete/{id}")
    public String deleteBoard(@PathVariable("id") Long id) {
        boardService.deleteById(id);
        System.out.println("삭제 성공");
        return "redirect:/"; // 삭제 후 index 페이지로 리디렉션
    }
}
