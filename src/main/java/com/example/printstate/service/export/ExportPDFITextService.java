package com.example.printstate.service.export;

import com.example.printstate.entity.Facture;
import com.example.printstate.entity.LigneFacture;
import com.example.printstate.repository.FactureRepository;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.AreaBreakType;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;


@Service
public class ExportPDFITextService {

    private final FactureRepository factureRepository;

    public ExportPDFITextService(FactureRepository factureRepository) {
        this.factureRepository = factureRepository;
    }

    public void export(OutputStream outputStream, Long idFacture) throws IOException {
        Facture facture = factureRepository.findById(idFacture).get();

        // Création d'un document
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(outputStream));
        Document document = new Document(pdfDoc);

        //Ajout logo IPI
        Image logo = new Image(ImageDataFactory.create("logo-ipi.jpg"));
        document.add(logo);

        //Ajout du Nom
        Paragraph title = new Paragraph(facture.getClient().getPrenom() + " " + facture.getClient().getNom());
        PdfFont fontNom = PdfFontFactory.createFont(StandardFonts.COURIER_BOLD);
        title.setFontSize(15f).setFont(fontNom);
        document.add(title);

        //Ajout numero de facture
        Paragraph numFac = new Paragraph("Facture n°: " + facture.getId());
        PdfFont fontNum = PdfFontFactory.createFont(StandardFonts.COURIER_BOLD);
        numFac.setFontSize(25f).setFont(fontNum);
        numFac.setTextAlignment(TextAlignment.CENTER);
        document.add(numFac);

        // création d'un tableau
        Table table = new Table(UnitValue.createPercentArray(4)).useAllAvailableWidth();

        // remplissage des cellules du tableau
        table.addCell("Articles");
        table.addCell("Quantité");
        table.addCell("Prix Unitaire");
        table.addCell("Sous Total");

        for (LigneFacture ligneFacture : facture.getLigneFactures()) {
            table.addCell(ligneFacture.getArticle().getLibelle());
            table.addCell("" + ligneFacture.getQuantite());
            table.addCell("" + ligneFacture.getArticle().getPrix());
            table.addCell("" + ligneFacture.getQuantite() * ligneFacture.getArticle().getPrix());
        }
        Cell cellTotalLibelle = new Cell(1, 2);
        cellTotalLibelle.add(new Paragraph("PRIX TOTAL"));
        table.addCell(cellTotalLibelle);

        table.addCell("" + facture.getTotal());

        document.add(table);

        document.close();

    }

    public void exportAllPDF(ServletOutputStream outputStream) throws IOException {
        factureRepository.findAll();

        // Création d'un document
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(outputStream));
        Document document = new Document(pdfDoc);


        for (Facture factureAll : factureRepository.findAll()) {


            //Ajout logo IPI
            Image logo = new Image(ImageDataFactory.create("logo-ipi.jpg"));
            document.add(logo);

            //Ajout du Nom
            Paragraph title = new Paragraph(factureAll.getClient().getPrenom() + " " + factureAll.getClient().getNom());
            PdfFont fontNom = PdfFontFactory.createFont(StandardFonts.COURIER_BOLD);
            title.setFontSize(15f).setFont(fontNom);
            document.add(title);

            //Ajout numero de facture
            Paragraph numFac = new Paragraph("Facture n°: " + factureAll.getId());
            PdfFont fontNum = PdfFontFactory.createFont(StandardFonts.COURIER_BOLD);
            numFac.setFontSize(25f).setFont(fontNum);
            numFac.setTextAlignment(TextAlignment.CENTER);
            document.add(numFac);

            // création d'un tableau
            Table table = new Table(UnitValue.createPercentArray(4)).useAllAvailableWidth();

            // remplissage des cellules du tableau
            table.addCell("Articles");
            table.addCell("Quantité");
            table.addCell("Prix Unitaire");
            table.addCell("Sous Total");

            for (LigneFacture ligneFacture : factureAll.getLigneFactures()) {
                table.addCell(ligneFacture.getArticle().getLibelle());
                table.addCell("" + ligneFacture.getQuantite());
                table.addCell("" + ligneFacture.getArticle().getPrix());
                table.addCell("" + ligneFacture.getQuantite() * ligneFacture.getArticle().getPrix());
            }
            Cell cellTotalLibelle = new Cell(1, 2);
            cellTotalLibelle.add(new Paragraph("PRIX TOTAL"));
            table.addCell(cellTotalLibelle);

            table.addCell("" + factureAll.getTotal());

            document.add(table);
            document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

        }

        document.close();


    }
}
