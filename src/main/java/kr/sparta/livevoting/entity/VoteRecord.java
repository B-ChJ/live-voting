package kr.sparta.livevoting.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity // 해당 클래스가 DB의 테이블과 연결되는(Mapping) 객체임을 명시한다.
@Table(name = "vote_record") // 객체(Entity) 클래스와 DB의 테이블을 연결해준다. (Mapping)
// 테이블 이름을 지정할 수 있음
@Getter // 클래스의 필드에 대한 Getter 메서드를 자동으로 생성해준다.
@NoArgsConstructor // 해당 클래스의 기본 생성자를 자동으로 만들어준다.
public class VoteRecord extends BaseTimeEntity {

    @Id // 아래 선언된 필드가 이 객체의 기본 키(pk)임을 명시해준다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성 전략을 정의한다. → IDENTITY : 자동 증가 방식
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // 1:N 연관관계 중 N에 해당되는 필드임을 정의해준다.
    // FetchType.LAZY : 지연로딩. 처음 객체를 가져올 때는 프록시 객체만 가져오고 쿼리는 실제로 데이터를 사용할 때 실행됨
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Vote vote;

    @ManyToOne(fetch = FetchType.LAZY)
    private Candidate candidate;

    public VoteRecord(Users user, Vote vote, Candidate candidate) {
        this.user = user;
        this.vote = vote;
        this.candidate = candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
}
