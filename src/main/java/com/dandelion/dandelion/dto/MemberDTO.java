package com.dandelion.dandelion.dto;

import com.dandelion.dandelion.entity.MemberEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor  // 기본 생성자를 만들어줌
@AllArgsConstructor // 모든 필드를 갖는 생성자를 만들어줌
@ToString // Lombok  toString() 메서드를 생성
public class MemberDTO {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String role;
    public static MemberDTO toMemberDTO(MemberEntity memberEntity){
        MemberDTO memberDTO = new MemberDTO();
        /*Entity에 감긴 값을  DTO로세팅하는 과정*/
        memberDTO.setId(memberEntity.getId());
        memberDTO.setEmail(memberEntity.getEmail());
        memberDTO.setPassword(memberEntity.getPassword());
        memberDTO.setName(memberEntity.getName());
        memberDTO.setRole(memberEntity.getRole());
        return memberDTO;
    }
}
