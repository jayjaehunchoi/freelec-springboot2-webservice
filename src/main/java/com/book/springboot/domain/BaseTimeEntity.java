package com.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //jpa entity 클래스들이 BaseTimeEntity를 상속할 경우 필드들도 칼럼으로 인식하도록 함
@EntityListeners(AuditingEntityListener.class) // BaseTimeEntity클래스에 Auditing 기능 포함
public class BaseTimeEntity {

    @CreatedDate // 생성 저장시 시간 자동 저장
    private LocalDateTime createDate;

    @LastModifiedDate //조회한 entity값 변경 시 시간 자동 저장
    private LocalDateTime modifiedDate;
}
