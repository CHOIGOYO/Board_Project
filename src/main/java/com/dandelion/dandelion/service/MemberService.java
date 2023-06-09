package com.dandelion.dandelion.service;

import com.dandelion.dandelion.dto.MemberDTO;
import com.dandelion.dandelion.entity.MemberEntity;
import com.dandelion.dandelion.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;


@RequiredArgsConstructor // 생성자 주입 사용하기
@Service
public class MemberService {
    private final MemberRepository memberRepository;


    //    로그인
    public MemberDTO login(MemberDTO memberDTO) {
        Optional<MemberEntity> byMemberEmail = memberRepository.findByEmail(memberDTO.getEmail());
        if (byMemberEmail.isPresent()) {
            MemberEntity memberEntity = byMemberEmail.get();
            if (memberEntity.getPassword().equals(memberDTO.getPassword())) {
                return MemberDTO.toMemberDTO(memberEntity);
            } else {
                System.out.println("로그인 실패: 비밀번호가 일치하지 않습니다.");
                return null;
            }
        } else {
            System.out.println("로그인 실패: 해당 이메일을 가진 회원정보가 없습니다.");
            return null;
        }
    }


//    회원가입요청시 이메일 중복확인 메서드

    public String SignUpFormSumitCheck(String email, String password, String confirmPassword) {
        Optional<MemberEntity> byEmail = memberRepository.findByEmail(email);

        if (!(byEmail.isPresent()) && password.equals(confirmPassword)) { // 조회결과 없고, 입력한 비밀번호와 비밀번호확인이 일치할경우 ok리턴
            return "EmailPWok";
        } else if (!(password.equals(confirmPassword))) { // 입력한 비밀번호와 비밀번호확인이 다른경우 PWno리턴
            return "PWno";
        } else if (byEmail.isPresent()) { // 입력한 이메일이 DB상 존재하는경우 Emailno리턴
            return "Emailno";
        } else {
            return "1234";
        }
    }

//    회원가입 이메일입력시 중복여부 확인 innerhtml에 들어가는 메서드

    public String emailDuplicateCheck(String email) {
        Optional<MemberEntity> byEmail = memberRepository.findByEmail(email);
        if (!(byEmail.isPresent())) { // 조회결과 없으면 사용가능 이메일
            return "ok";
        } else {
            return "no";
        }
    }

    public void save(MemberDTO memberDTO) {
        //        1. dto -> entity 변환
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        //        2. repository의 save메서드 호출
        try {
            memberRepository.save(memberEntity); // jpa가 제공해주는 save 메서드
            System.out.println("회원테이블 DB저장시도");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //    이메일로 회원정보 조회
    public MemberDTO infoUpDateForm(String email) {
        Optional<MemberEntity> byEmail = memberRepository.findByEmail(email);
        if (byEmail.isPresent()) { // Optional 객체가 값을 가지고 있다면 true
            return MemberDTO.toMemberDTO(byEmail.get());
        } else {
            return null;
        }
    }

    // 아이디 정보로 회원정보 삭제
    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }

    // 회원정보 수정
    public void infoUpDate(MemberDTO memberDTO) {
        MemberEntity memberEntity = memberRepository.getOne(memberDTO.getId());
        memberEntity.setEmail(memberDTO.getEmail());
        memberEntity.setPassword(memberDTO.getPassword());
        memberEntity.setName(memberDTO.getName());
        memberRepository.save(memberEntity); // 수정된 내용을 데이터베이스에 반영
    }
}


