package kr.sparta.livevoting.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter // 클래스의 필드에 대한 Getter 메서드를 자동으로 생성해준다.
@Entity // 해당 클래스가 DB의 테이블과 연결되는(Mapping) 객체임을 명시한다.
@NoArgsConstructor // 해당 클래스의 기본 생성자를 자동으로 만들어준다.
@Table(name = "users") // 객체(Entity) 클래스와 DB의 테이블을 연결해준다. (Mapping)
// 테이블 이름을 지정할 수 있음
public class Users extends BaseTimeEntity{

    @Id // 아래 선언된 필드가 이 객체의 기본 키(pk)임을 명시해준다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성 전략을 정의한다. → IDENTITY : 자동 증가 방식
    private Long id;

    @Column(unique = true, nullable = false) // 아래 선언된 필드를 DB의 컬럼과 연결해준다. (Mapping)
    // 컬럼의 속성을 정할 수 있음
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Enumerated(EnumType.STRING) // 열거형 타입을 DB 컬럼과 연결해준다. → STRING : Enum의 name값을 DB에 저장
    @Column(nullable = false)
    private UserRole role;

    @OneToMany(mappedBy = "author") // 1:N 연관관계 중 1에 해당되는 필드임을 정의해준다.
    private List<Vote> votes; // 사용자가 생성한 Vote 리스트

    public Users(String loginId, String password, String nickname) {
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
        this.role = UserRole.USER;
    }

    enum UserRole {
        USER,
        ADMIN
    }
}
