package com.ll.mbti.article;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//엔티티만으로는 데이터를 저장하거나 조회 할 수 없기 때문에 데이터 처리를 위해 실제 데이터베이스와 연동하는 JPA 리포지터리 생성
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    List<Article> findByTitleLike(String title);
}
