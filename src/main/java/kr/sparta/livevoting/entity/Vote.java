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

    @OneToMany(mappedBy = "vote", cascade = CascadeType.ALL, orphanRemoval = true)
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

    public enum VoteStatus {
        OPEN, CLOSED
    }
}
