package kr.sparta.livevoting.repository;

import kr.sparta.livevoting.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {

}
