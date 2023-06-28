package com.example.printstate.controller.export;

import com.example.printstate.service.export.ClientExportCVSService;
import com.example.printstate.service.export.ClientExportXLSXService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Controller pour réaliser export des clients.
 */
@Controller
@RequestMapping("export")
public class ExportClientController {

    private final ClientExportCVSService clientExportCVSService;

    private final ClientExportXLSXService clientExportXLSXService;

    public ExportClientController(ClientExportCVSService clientExportCVSService, ClientExportXLSXService clientExportXLSXService) {
        this.clientExportCVSService = clientExportCVSService;
        this.clientExportXLSXService = clientExportXLSXService;
    }

    /**
     * Export des clients au format CSV.
     */
    @GetMapping("/clients/csv")
    public void clientsCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"export-clients.csv\"");
        PrintWriter writer = response.getWriter();
        clientExportCVSService.export(writer);
    }

    /**
     * Export des clients au format XLSX.
     */
    @GetMapping("/clients/xlsx")
    public void clientsXLSX(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=\"clients.xlsx\"");
        clientExportXLSXService.export(response.getOutputStream());
    }

}
