package com.dandelion.dandelion.service;

import com.dandelion.dandelion.dto.BoardDTO;
import com.dandelion.dandelion.entity.BoardEntity;
import com.dandelion.dandelion.entity.MemberEntity;
import com.dandelion.dandelion.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
