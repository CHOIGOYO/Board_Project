package com.dandelion.dandelion.service;

import com.dandelion.dandelion.dto.CommentsDTO;
import com.dandelion.dandelion.entity.CommentsEntity;
import com.dandelion.dandelion.repository.BoardRepository;
import com.dandelion.dandelion.repository.CommentsRepository;
import com.dandelion.dandelion.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor // 생성자 주입 사용하기
@Service
public class CommentService {
    private final CommentsRepository commentsRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    //  저장된 댓글의 게시글 ID를 반환
    public Long saveComment(CommentsDTO commentsDTO) {
        CommentsEntity commentsEntity = CommentsEntity.toCommentsEntity(commentsDTO, memberRepository, boardRepository);
        CommentsEntity savedComment = commentsRepository.save(commentsEntity);
        return savedComment.getBoard().getId();
    }

    public List<CommentsDTO> getCommentsByBoardId(Long boardId) {
        List<CommentsEntity> commentsEntities = commentsRepository.findByBoardId(boardId);
        return commentsEntities.stream()
                .map(CommentsDTO::toCommentsDTO)
                .collect(Collectors.toList());
    }
}
