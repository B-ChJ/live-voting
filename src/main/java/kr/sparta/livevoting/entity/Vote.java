package kr.sparta.livevoting.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "votes")
@Getter
@NoArgsConstructor
public class Vote extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String title;

    @Enumerated(EnumType.STRING)
    private VoteStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Users author;

    @OneToMany(mappedBy = "vote")
    private List<VoteRecord> voteRecordList;

    @OneToMany(mappedBy = "vote")
    private List<Candidate> candidateList;

    public enum VoteStatus {
        OPEN, CLOSED
    }
}
