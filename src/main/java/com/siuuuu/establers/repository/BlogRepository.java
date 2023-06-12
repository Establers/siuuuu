package com.siuuuu.establers.repository;

import com.siuuuu.establers.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
