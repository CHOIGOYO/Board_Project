package com.dandelion.dandelion.controller;

import com.dandelion.dandelion.dto.MemberDTO;
import com.dandelion.dandelion.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor // 생성자 주입 사용하기

@Controller
public class MemberController {
    private final MemberService memberService;

    //    로그인페이지 반환
    @GetMapping("/member/login")
    public String loginForm() {
        return "login";
    }
    //    회원가입페이지 반환
    @GetMapping("/member/signUp")
    public String signUpForm(){
        return "signUp";
    }

    //    로그인 요청
    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession httpSession){
        try {
            MemberDTO loginResult = memberService.login(memberDTO);
            // 세션에 정보 저장
            httpSession.setAttribute("email",loginResult.getEmail());
            httpSession.setAttribute("id",loginResult.getId());
            httpSession.setAttribute("name" ,loginResult.getName());
            if (loginResult != null) { // 로그인 성공했을 경우
                return "main"; // 메인으로
            }
        } catch (Exception e){
            e.printStackTrace();
        }
//        로그인에 실패한 경우
        System.out.println("로그인 요청실패");
        return "redirect:/member/login"; // 로그인 폼으로 반환
    }


    //    회원가입요청시 이메일 중복체크 비밀번호와 비밀번호 확인체크
    @PostMapping("/SignUpForm/SignUpFormSumitCheck")
    public @ResponseBody String SignUpFormSumitCheck(@RequestParam("email") String email, @RequestParam("password")
    String password, @RequestParam("confirmPassword")String confirmPassword){ // @ResponseBody ajax사용시 필요 어노테이션

        System.out.println("email = " + email);
        String checkresult = memberService.SignUpFormSumitCheck(email, password, confirmPassword);
        System.out.println(checkresult);

        return checkresult;

    }

    //    회원가입 이메일 입력시 innerhtml에 들어갈 메서드
    @PostMapping("/SignUpForm/emailDuplicateCheck")
    public @ResponseBody String emailDuplicateCheck(@RequestParam("email") String email){
        String emailChek = memberService.emailDuplicateCheck(email);
        return emailChek;

    }

    //  회원가입을 위한 메서드 1 입력받은 값을 userService에 넘긴다
    @PostMapping("/member/save") // 요청이 들어오면 메서드를 실행하겠다
    public String save(@ModelAttribute MemberDTO memberDTO){ // SignUpForm에서 입력받은 값의 name이 DTO의 멤버 명과 같을 때 세팅됨

        System.out.println("MemberController.save");
        System.out.println("memberDTO = " + memberDTO);
        memberService.save(memberDTO); // service객체에 dto객체를 넘김
        return "redirect:/member/login";
    }


}
