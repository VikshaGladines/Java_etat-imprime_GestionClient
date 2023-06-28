package com.example.printstate.service.mapper;

import com.example.printstate.dto.ArticleDto;
import com.example.printstate.entity.Article;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapper {

    public ArticleDto articleDto(Article article) {
        return new ArticleDto(article.getId(), article.getLibelle(), article.getPrix(), article.getStock());
    }

}
