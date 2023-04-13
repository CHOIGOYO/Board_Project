package com.dandelion.dandelion.repository;

import com.dandelion.dandelion.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    List<BoardEntity> findByCategories(String categories);
}

