package com.sharecare.category.model;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepository extends PagingAndSortingRepository<Category, String> {
    Category findByUrl(String url);
}
