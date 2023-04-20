package com.dandelion.dandelion.repository;

import com.dandelion.dandelion.entity.CommentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<CommentsEntity, Long> {
    List<CommentsEntity> findByBoardId(Long boardId);
}
