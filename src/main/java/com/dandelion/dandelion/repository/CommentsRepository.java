package com.dandelion.dandelion.repository;

import com.dandelion.dandelion.entity.CommentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<CommentsEntity, Long> {
}
