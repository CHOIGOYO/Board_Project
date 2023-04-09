package com.dandelion.dandelion.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 모든 JPA 엔티티들이 BaseTimeEntity를 상속 받을 경우 필드도 컬럼으로 인식하도록 한다.
@EntityListeners(AuditingEntityListener.class) // BaseTimeEntity 클래스에 Auditing 기능을 포함시킨다.
public class BaseTimeEntity {

    @CreatedDate // 엔티티가 생성될 때 생성된 시간이 자동 저장된다.
    private LocalDateTime localDateTime;

    @LastModifiedDate // 조회한 엔티티가 수정될 때 수정된 시간이 자동 저장된다.
    private LocalDateTime modifiedDate;
}
