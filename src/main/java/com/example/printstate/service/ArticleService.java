package com.example.printstate.service;

import com.example.printstate.dto.ArticleDto;

import java.util.List;

public interface ArticleService {
    List<ArticleDto> findAll();

    ArticleDto findById(long articleId);

    ArticleDto create(ArticleDto dto);

    ArticleDto modifierStock(Long id, Integer quantity);
}
