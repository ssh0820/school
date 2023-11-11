package com.zerobase.school.news.repository;

import com.zerobase.school.news.domain.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long>, NewsCustomRepository {

}
