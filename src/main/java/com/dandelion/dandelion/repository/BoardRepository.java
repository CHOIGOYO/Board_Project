package com.dandelion.dandelion.repository;

import com.dandelion.dandelion.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
}
