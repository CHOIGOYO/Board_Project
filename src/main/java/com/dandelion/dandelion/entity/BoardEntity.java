package com.dandelion.dandelion.entity;

import com.dandelion.dandelion.dto.BoardDTO;
import com.dandelion.dandelion.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Setter
@NoArgsConstructor // 기본 생성자가 생성
@AllArgsConstructor
@Table(name = "baordtable") // db에 생성될 테이블 이름
public class BoardEntity extends BaseTimeEntity{
    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 시퀀스의 역할 auto_increment
    @Column(name="id" , nullable = false)
    private Long id; // 게시글 넘버
    @Column(name="title" , nullable = false,length = 100)
    private String title; // 제목
    @Column(name="content" , nullable = false)
    private String content; // 내용
    @Column(name="categories" , nullable = false)
    private String categories; // 카테고리

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity member;
    @OneToMany(mappedBy = "board" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CommentsEntity> comments = new ArrayList<>();


    public static BoardEntity toBoardEntity(BoardDTO boardDTO, MemberEntity member) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId(boardDTO.getId());
        boardEntity.setCategories(boardDTO.getCategories());
        boardEntity.setTitle(boardDTO.getTitle());
        boardEntity.setContent(boardDTO.getContent());
        boardEntity.setMember(member);
        return boardEntity;
    }

    public String getMemberName() {
        return member != null ? member.getName() : "";
    }

}