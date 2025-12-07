package kr.sparta.livevoting.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity // 해당 클래스가 DB의 테이블과 연결되는(Mapping) 객체임을 명시한다.
@Table(name = "votes") // 객체(Entity) 클래스와 DB의 테이블을 연결해준다. (Mapping)
// 테이블 이름을 지정할 수 있음
@Getter // 클래스의 필드에 대한 Getter 메서드를 자동으로 생성해준다.
@NoArgsConstructor // 해당 클래스의 기본 생성자를 자동으로 만들어준다.
public class Vote extends BaseTimeEntity{

    @Id // 아래 선언된 필드가 이 객체의 기본 키(pk)임을 명시해준다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성 전략을 정의한다. → IDENTITY : 자동 증가 방식
    private Long id;

    @Column(nullable = false, length = 30) // 아래 선언된 필드를 DB의 컬럼과 연결해준다. (Mapping)
    // 컬럼의 속성을 정할 수 있음
    private String title;

    @Enumerated(EnumType.STRING) // 열거형 타입을 DB 컬럼과 연결해준다. → STRING : Enum의 name값을 DB에 저장
    private VoteStatus status;

    @ManyToOne(fetch = FetchType.LAZY) // 1:N 연관관계 중 N에 해당되는 필드임을 정의해준다.
    // FetchType.LAZY : 지연로딩. 처음 객체를 가져올 때는 프록시 객체만 가져오고 쿼리는 실제로 데이터를 사용할 때 실행됨
    @JoinColumn(name = "author_id") // 연관관계 연결 시 외래키 컬럼을 지정한다.
    private Users author;

    @OneToMany(mappedBy = "vote", cascade = CascadeType.ALL) // 1:N 연관관계 중 1에 해당되는 필드임을 정의해준다.
    // 연관된 객체와의 영속성 전이를 설정할 수 있음 → CascadeType.ALL : 영속성 객체의 모든 상태 변화를 반영한다.
    private List<VoteRecord> voteRecordList;

    @OneToMany(mappedBy = "vote", cascade = CascadeType.ALL, orphanRemoval = true)
    // 1:N 연관관계 중 1에 해당되는 필드임을 정의해준다.
    private List<Candidate> candidateList;

    public Vote(String title) {
        this.title = title;
        this.status = VoteStatus.OPEN;
    }

    public void setAuthor(Users author) {
        this.author = author;
    }

    public void setCandidateList(List<Candidate> candidateList) {
        this.candidateList = candidateList;
    }

    public void setStatus(VoteStatus status) {
        this.status = status;
    }

    public enum VoteStatus {
        OPEN, CLOSED
    }
}
