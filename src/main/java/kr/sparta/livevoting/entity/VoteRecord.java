package kr.sparta.livevoting.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vote_record")
@Getter
@NoArgsConstructor
public class VoteRecord extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
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
