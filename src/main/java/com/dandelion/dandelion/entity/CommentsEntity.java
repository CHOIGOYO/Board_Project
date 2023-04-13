package com.dandelion.dandelion.entity;

import com.dandelion.dandelion.dto.CommentsDTO;
import com.dandelion.dandelion.repository.BoardRepository;
import com.dandelion.dandelion.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Getter
@Setter
@NoArgsConstructor // 기본 생성자가 생성
@AllArgsConstructor
@Table(name = "conmmentstable") // db에 생성될 테이블 이름
public class CommentsEntity extends BaseTimeEntity{
        @Id // pk
        @GeneratedValue(strategy = GenerationType.IDENTITY) // 시퀀스의 역할 auto_increment
        @Column(name="id" , nullable = false)
        private Long id; // 댓글 고유번호
        @Column(name="title" , nullable = false,length = 100)
        private String content; // 내용

        @ManyToOne
        @JoinColumn(name = "member_id")
        private MemberEntity member;
        @ManyToOne
        @JoinColumn(name = "board_id")
        private BoardEntity board;

        public static CommentsEntity toCommentsEntity(CommentsDTO commentsDTO, MemberRepository memberRepository, BoardRepository boardRepository) {
                CommentsEntity commentsEntity = new CommentsEntity();
                commentsEntity.setId(commentsDTO.getId());
                commentsEntity.setContent(commentsDTO.getContent());

                if (commentsDTO.getMemberId() != null) {
                        Optional<MemberEntity> memberOpt = memberRepository.findById(commentsDTO.getMemberId());
                        memberOpt.ifPresent(commentsEntity::setMember);
                }

                if (commentsDTO.getBoardId() != null) {
                        Optional<BoardEntity> boardOpt = boardRepository.findById(commentsDTO.getBoardId());
                        boardOpt.ifPresent(commentsEntity::setBoard);
                }

                return commentsEntity;
        }

}

