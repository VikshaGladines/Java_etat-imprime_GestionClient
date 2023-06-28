package com.example.printstate.service.impl;

import com.example.printstate.dto.ArticleDto;
import com.example.printstate.entity.Article;
import com.example.printstate.repository.ArticleRepository;
import com.example.printstate.service.ArticleService;
import com.example.printstate.service.mapper.ArticleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * Service contenant les actions métiers liées aux articles.
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;
    private ArticleMapper articleMapper;

    public ArticleServiceImpl(ArticleRepository articleRepository, ArticleMapper articleMapper) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleRepository.findAll().stream().map(article -> articleMapper.articleDto(article)).collect(toList());
    }

    @Override
    public ArticleDto findById(long articleId) {
        Optional<Article> article = articleRepository.findById(articleId);
        return articleMapper.articleDto(article.get());
    }

    @Override
    public ArticleDto create(ArticleDto dto) {
        Article article = new Article();
        article.setLibelle(dto.getLibelle());
        article.setPrix(dto.getPrix());
        articleRepository.save(article);
        return articleMapper.articleDto(article);
    }

    @Override
    public ArticleDto modifierStock(Long id, Integer quantity) {
        Article article = articleRepository.findById(id).get();
        article.setStock(quantity);
        return articleMapper.articleDto(article);
    }

}
