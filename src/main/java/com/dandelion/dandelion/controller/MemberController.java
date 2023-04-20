package com.dandelion.dandelion.controller;

import com.dandelion.dandelion.dto.MemberDTO;
import com.dandelion.dandelion.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor // 생성자 주입 사용하기

@Controller
public class MemberController {
    private final MemberService memberService;

    //    로그인페이지 반환
    @GetMapping("/member/loginForm")
    public String loginForm() {
        return "views/login";
    }
    //    회원가입페이지 반환
    @GetMapping("/member/signUp")
    public String signUpForm(){
        return "views/signUp";
    }

    //    로그인 요청
    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession httpSession) {
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null) {
            httpSession.setAttribute("email", loginResult.getEmail());
            httpSession.setAttribute("id", loginResult.getId());
            httpSession.setAttribute("name", loginResult.getName());
            httpSession.setAttribute("role", loginResult.getRole());
            return "redirect:/";
        } else {
            System.out.println("로그인 실패: 로그인 정보가 올바르지 않습니다.");
            return "redirect:/member/login";
        }
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
        return "redirect:/member/loginForm";
    }

    // 마이페이지
    @GetMapping("/member/mypage")
    public String mypage(HttpSession session, Model model){
        Object loginEmail = session.getAttribute("email"); // 세션유지) 회원의정보를 얻어오기
        System.out.println("@GetMapping(\"/member/mypage\") 의 이메일 정보 => "+loginEmail);
        String email = (String)loginEmail; // 강제 형변환
        MemberDTO memberDTO = memberService.infoUpDateForm(email); // DB에서 email정보로 회원정보를 찾아옴
        model.addAttribute("memberUpdate", memberDTO); // 가져온 값을 memberUpdate 담는다
        return "views/mypage";
    }

    // 회원정보 수정 후 마이페이지 반환
    @PostMapping("/member/update")
    public String upDateUserInfo(@ModelAttribute MemberDTO memberDTO){
        memberService.infoUpDate(memberDTO);  // 수정버튼 눌렀을 때 디비에 업데이트 되는 메서드 호출
        return "redirect:/member/mypage"; // 수정된 정보가 확인되는 마이페이지 반환
    }


    // 회원탈퇴 요청받으면 실행 될 메서드
    @GetMapping("/member/delete/{id}")
    public String deleteById(@PathVariable Long id ,HttpSession session){
        memberService.deleteById(id); // 회원정보를 디비에서 삭제하는 서비스 메서드
        session.invalidate(); // 세션 무효화
        return "redirect:/"; // 회원탈퇴 후 index페이지로 반환
    }

    //   로그아웃요청 // 세션 초기화
    @GetMapping("/member/logout")
    public String logOut(HttpSession session){
        session.invalidate(); // 세션 무효화
        return "redirect:/";
    }
}
