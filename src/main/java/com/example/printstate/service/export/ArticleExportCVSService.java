package com.example.printstate.service.export;

import com.example.printstate.entity.Article;
import com.example.printstate.entity.Client;
import com.example.printstate.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.List;

@Service
public class ArticleExportCVSService {

    private final ArticleRepository articleRepository;

    public ArticleExportCVSService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void export(PrintWriter writer) {
        writer.println("Libelle;Prix;Stock");
        List<Article> articles = articleRepository.findAll();
        for (Article article : articles) {
            String line = article.getLibelle() + ";" + article.getPrix() + ";" + article.getStock() ;
            writer.println(line);
        }

    }


}
