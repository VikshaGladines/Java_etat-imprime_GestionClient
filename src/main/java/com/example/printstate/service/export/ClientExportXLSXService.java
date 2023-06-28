package com.example.printstate.service.export;

import com.example.printstate.entity.Article;
import com.example.printstate.entity.Client;
import com.example.printstate.entity.Facture;
import com.example.printstate.entity.LigneFacture;
import com.example.printstate.repository.ClientRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

@Service
public class ClientExportXLSXService {

    private final ClientRepository clientRepository;

    public static final int COL_DES = 0;
    public static final int COL_QUAN = 1;
    public static final int COL_PRIX = 2;
    public static final int COL_TOTAL = 1;

    public ClientExportXLSXService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void export(ServletOutputStream outputStream) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        //Apache POI



        int iRow = 1;

        for (Client client : clientRepository.findAll()) {
            Sheet sheet = workbook.createSheet(client.getNom());

            Row row = sheet.createRow(1);

            Row nom = sheet.createRow(0);
            Cell cellNom = row.createCell(0);
            cellNom.setCellValue("Nom :");

            Cell cellNomValue = row.createCell(1);
            cellNomValue.setCellValue(client.getNom());


            Row row2 = sheet.createRow(2);

            Row prenom = sheet.createRow(0);
            Cell cellPrenom = row2.createCell(0);
            cellPrenom.setCellValue("Prenom :");

            Cell cellPrenomValue = row2.createCell(1);
            cellPrenomValue.setCellValue(client.getPrenom());


            List<Facture> factures = client.getFactures();
            for (Facture facture : factures) {

                Row row3 = sheet.createRow(3);

                Row factureF = sheet.createRow(0);
                Cell cellFacture = row3.createCell(0);
                cellFacture.setCellValue("Facture :");

                Cell cellFactureValue = row3.createCell(1);
                cellFactureValue.setCellValue(facture.getId());



                Sheet sheet2 = workbook.createSheet("Facture n°" + facture.getId());


                Cell cellDesignation = row.createCell(COL_DES);
               // cellDesignation.setCellValue();

                Cell cellQuantité = row.createCell(COL_QUAN);
                // cellQuantité.setCellValue(article.getStock);

                Cell cellPrix = row.createCell(COL_PRIX);
                // cellPrix.setCellValue(article.getPrix);

                Cell cellTotal = row.createCell(COL_TOTAL);
                // cellTotal.setCellValue(article.getTotal);




        }


            workbook.write(outputStream);
            workbook.close();

        }


    }
}
