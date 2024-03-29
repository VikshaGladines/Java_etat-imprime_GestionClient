package com.example.printstate.controller.export;

import com.example.printstate.service.export.ArticleExportCVSService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Controller pour réaliser export des articles.
 */
@Controller
@RequestMapping("export")
public class ExportArticleController {

    private final ArticleExportCVSService articleExportCVSService;

    public ExportArticleController(ArticleExportCVSService articleExportCVSService) {
        this.articleExportCVSService = articleExportCVSService;
    }

    /**
     * Export des articles au format CSV.
     */
    @GetMapping("/articles/csv")
    public void articlesCSV(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"export-articles.csv\"");
        PrintWriter writer = response.getWriter();
        articleExportCVSService.export(writer);
    }

}
