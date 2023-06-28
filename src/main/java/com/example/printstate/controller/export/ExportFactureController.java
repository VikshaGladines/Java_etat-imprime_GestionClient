package com.example.printstate.controller.export;


import com.example.printstate.service.export.ExportPDFITextService;
import com.example.printstate.service.export.FactureExportXLSXService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller pour réaliser export des factures.
 */
@Controller
@RequestMapping("export")
public class ExportFactureController {

    private final FactureExportXLSXService facturesExportXLSX;

    private final ExportPDFITextService exportPDFITextService;

    public ExportFactureController(FactureExportXLSXService facturesExportXLSX, ExportPDFITextService exportPDFITextService) {
        this.facturesExportXLSX = facturesExportXLSX;
        this.exportPDFITextService = exportPDFITextService;
    }

    /**
     * Export des factures d'un client au format XLSX.
     */
    @GetMapping("/clients/{id}/factures/xlsx")
    public void clientGetFacturesXLSX(@PathVariable Long id, HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=\"client-" + id + "-factures.xlsx\"");
        facturesExportXLSX.export(id, response.getOutputStream());
    }


    /**
     * Export de toutes les factures de l'application au format CSV.
     */
    @GetMapping("/factures/xlsx")
    public void facturesXLSX(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=\"factures.xlsx\"");
        facturesExportXLSX.export(response.getOutputStream());
    }

    /**
     * Export d'une facture au format PDF.
     */
    @GetMapping("/factures/{id}/pdf")
    public void facturesPDF(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) throws IOException {
        exportPDFITextService.export(response.getOutputStream(), id);
    }


    //Export d'une facture au format pdf

    @GetMapping("/factures/all/pdf")
    public void allfacturesPDF(HttpServletRequest request, HttpServletResponse response ) throws IOException {
        exportPDFITextService.exportAllPDF(response.getOutputStream());
    }
}
