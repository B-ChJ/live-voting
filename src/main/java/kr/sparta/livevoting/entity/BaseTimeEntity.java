package kr.sparta.livevoting.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class) // Entity의 변경 사항(특정 이벤트)의 발생을 감지하고
// 자동으로 특정 작업을 수행하게 해준다.
@Getter // 클래스의 필드에 대한 Getter 메서드를 자동으로 생성해준다.
@MappedSuperclass // 객체 클래스에서 공통으로 사용되는 정보를 상속할 수 있게 만들어준다. 이 클래스는 DB 테이블과 연결되지 않음
public abstract class BaseTimeEntity {

    @CreatedDate // 객체가 처음 생성된 일시를 자동으로 기록한다.
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate // 객체가 마지막으로 수정된 일시를 자동으로 기록한다.
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
