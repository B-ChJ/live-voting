package kr.sparta.livevoting.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity // 해당 클래스가 DB의 테이블과 연결되는(Mapping) 객체임을 명시한다.
@Table(name = "candidates") // 객체(Entity) 클래스와 DB의 테이블을 연결해준다. (Mapping)
// 테이블 이름을 지정할 수 있음
@Getter // 클래스의 필드에 대한 Getter 메서드를 자동으로 생성해준다.
@NoArgsConstructor // 해당 클래스의 기본 생성자를 자동으로 만들어준다.
public class Candidate {

    @Id // 아래 선언된 필드가 이 객체의 기본 키(pk)임을 명시해준다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성 전략을 정의한다. → IDENTITY : 자동 증가 방식
    private Long id;

    @Column(nullable = false) // 아래 선언된 필드를 DB의 컬럼과 연결해준다. (Mapping)
    // 컬럼의 속성을 정할 수 있음
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Vote vote;

    public Candidate(String name, Vote vote) {
        this.name = name;
        this.vote = vote;
    }
}
