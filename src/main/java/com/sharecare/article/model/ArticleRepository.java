package com.sharecare.article.model;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ArticleRepository extends PagingAndSortingRepository<Article, String> {

    Article findByUrl(String url);

    List<Article> findByCategory(String category);
}
