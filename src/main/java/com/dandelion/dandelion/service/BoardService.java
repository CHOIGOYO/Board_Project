package com.dandelion.dandelion.service;

import com.dandelion.dandelion.dto.BoardDTO;
import com.dandelion.dandelion.entity.BoardEntity;
import com.dandelion.dandelion.entity.MemberEntity;
import com.dandelion.dandelion.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor // 생성자 주입 사용하기
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public List<BoardEntity> getBoardsByCategory(String category) {
        return boardRepository.findByCategories(category);
    }

    public BoardEntity toBoardEntity(BoardDTO boardDTO, MemberEntity member) {
        return BoardEntity.toBoardEntity(boardDTO, member);
    }

    public BoardDTO toBoardDTO(BoardEntity boardEntity) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(boardEntity.getId());
        boardDTO.setTitle(boardEntity.getTitle());
        boardDTO.setContent(boardEntity.getContent());
        boardDTO.setCategories(boardEntity.getCategories());
        boardDTO.setMemberName(boardEntity.getMember().getName());
        boardDTO.setLocalDateTime(boardEntity.getLocalDateTime());
        return boardDTO;
    }

    // 아이디 정보로 회원정보 삭제
    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    }
    
    // 게시물 수정 후 업데이트
    public void updateBoard(Long id, BoardDTO boardDTO) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);

        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            // 수정 정보 설정
            boardEntity.setTitle(boardDTO.getTitle());
            boardEntity.setContent(boardDTO.getContent());
            boardEntity.setCategories(boardDTO.getCategories());
            // 저장
            boardRepository.save(boardEntity);
        } else {
            System.out.println("게시글 수정 실패.");
        }
    }

}
