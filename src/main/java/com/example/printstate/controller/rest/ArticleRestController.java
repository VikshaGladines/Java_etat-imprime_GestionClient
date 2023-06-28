package com.example.printstate.controller.rest;

import com.example.printstate.dto.ArticleDto;
import com.example.printstate.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller pour exposition d'api REST des articles.
 */
@RestController
@RequestMapping("rest/articles")
public class ArticleRestController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public ArticleDto post(@RequestBody ArticleDto dto) {
        return articleService.create(dto);
    }

    @PostMapping("{id}/stock")
    public ArticleDto modifierStock(
            @PathVariable Long id,
            @RequestParam Integer quantity) {
        return articleService.modifierStock(id, quantity);
    }

    /**
     * Exposition d'une api déclenchée sur l'url http://..../articles.
     *
     * @return la liste des articles au format JSON. Voir la classe ArticleDto pour le détail du format.
     */
    @GetMapping
    public List<ArticleDto> getArticles() {
        return articleService.findAll();
    }

}
