package com.dandelion.dandelion.entity;

import com.dandelion.dandelion.dto.MemberDTO;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor // 기본 생성자가 생성
@AllArgsConstructor
@Table(name = "membertable") // db에 생성될 테이블 이름
public class MemberEntity extends BaseTimeEntity{ // BaseEntity를 상속
    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 시퀀스의 역할 auto_increment
    @Column(name="id" , nullable = false)
    private Long id; // 회원번호아이디
    @Column(name ="email",nullable = false , unique = true ,length = 50)
    private String email; // 회원이메일
    @Column(name ="password",nullable = false,length = 100)
    private String password; // 회원비밀번호
    @Column(name ="name",nullable = false,length = 50)
    private String name; // 회원이름
    @Column(name ="role",nullable = false)
    private String role = "user"; // 역할 디폴트 user

    @OneToMany(mappedBy = "member" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BoardEntity> boards = new ArrayList<>();
    @OneToMany(mappedBy = "member" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CommentsEntity> comments = new ArrayList<>();

    public static MemberEntity toMemberEntity(MemberDTO memberDTO){
        MemberEntity memberEntity = new MemberEntity();
        /*DTO에 감긴 값을 Entity로 세팅하는 과정*/
        memberEntity.setId(memberDTO.getId());
        memberEntity.setName(memberDTO.getName());
        memberEntity.setEmail(memberDTO.getEmail());
        memberEntity.setPassword(memberDTO.getPassword());
        memberEntity.setRole(memberDTO.getRole() == null ? "user" : memberDTO.getRole());
        return memberEntity;
    }
}