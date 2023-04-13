package com.dandelion.dandelion.dto;

import com.dandelion.dandelion.entity.CommentsEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor  // 기본 생성자를 만들어줌
@AllArgsConstructor // 모든 필드를 갖는 생성자를 만들어줌
@ToString // Lombok  toString() 메서드를 생성
public class CommentsDTO {
    private Long id; // 댓글 고유번호
    private Long boardId; // 부모글의 고유번호
    private String content; // 내용
    private Long memberId; // 작성자의 ID
    private String memberName; // 작성자 이름 추가

    public static CommentsDTO toCommentsDTO(CommentsEntity commentsEntity) {
        CommentsDTO commentsDTO = new CommentsDTO();
        commentsDTO.setId(commentsEntity.getId());
        commentsDTO.setContent(commentsEntity.getContent());

        if (commentsEntity.getMember() != null) {
            commentsDTO.setMemberId(commentsEntity.getMember().getId());
            commentsDTO.setMemberName(commentsEntity.getMember().getName());
        }

        if (commentsEntity.getBoard() != null) {
            commentsDTO.setBoardId(commentsEntity.getBoard().getId());
        }

        return commentsDTO;
    }

}
