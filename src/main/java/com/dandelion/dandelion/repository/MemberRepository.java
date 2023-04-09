package com.dandelion.dandelion.repository;

import com.dandelion.dandelion.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// JpaRepository 상속
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
//    이메일로 회원정보 조회
    Optional<MemberEntity> findByEmail(String email);
}
