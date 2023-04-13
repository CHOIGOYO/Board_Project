package com.dandelion.dandelion.dto;

import com.dandelion.dandelion.entity.BoardEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor  // 기본 생성자를 만들어줌
@AllArgsConstructor // 모든 필드를 갖는 생성자를 만들어줌
@ToString // Lombok  toString() 메서드를 생성
public class BoardDTO {
    private Long id; // 게시글 넘버
    private String title; // 제목
    private Long memberId; // 작성자의 ID
    private String memberName; // 작성자 이름 추가
    private String content; // 내용
    private String categories; // 카테고리
    private LocalDateTime localDateTime; // 등록시간
    private LocalDateTime modifiedDate; // 수정시간

    public static BoardDTO toboardDTO(BoardEntity boardEntity){
        BoardDTO boardDTO = new BoardDTO();
        /*Entity에 감긴 값을  DTO로세팅하는 과정*/
        boardDTO.setId(boardEntity.getId());
        boardDTO.setCategories(boardEntity.getCategories());
        boardDTO.setTitle(boardEntity.getTitle());
        boardDTO.setContent(boardEntity.getContent());
        boardDTO.setLocalDateTime(boardEntity.getLocalDateTime());
        boardDTO.setModifiedDate(boardEntity.getModifiedDate());

        // 작성자 이름 설정
        if (boardEntity.getMember() != null) {
            boardDTO.setMemberId(boardEntity.getMember().getId());
            boardDTO.setMemberName(boardEntity.getMember().getName());
        }

        return boardDTO;
    }


}
