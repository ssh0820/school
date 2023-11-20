package com.zerobase.school.board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {
    Board findBybId(Long bId);
    void deleteBybId(Long bId);
}