package com.zerobase.school.school.repository;

import com.zerobase.school.school.domain.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolRepository extends JpaRepository<School,Long> {

    boolean existsBySchoolCode(String schoolCode);
    Optional<School> findBySchoolType(String schoolType);
    Optional<School> findByNameContaining(String SchoolName);
    Optional<School> findByAddressContaining(String district);

}
