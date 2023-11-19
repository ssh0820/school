package com.zerobase.school.board.users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserId(Long id);
    int emailCount(String email);}