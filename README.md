# TP

# Fonctionnement
Ouvrir le projet dans eclipse ou intellij.
Démarrer l'application avec la classe 'main' : com.example.printstate.DemoApplication
Se connecter à la home page en accédant à l'url : http://localhost:8080/
Il s'agit d'une application Spring boot intégrant une petite base de données en mémoire.
L'application simule la gestion d'une base d'articles de clients et de factures.

# Data
Améliorer ses jeux de données (voir classe com.example.printstate.service.InitData):
* Ajouter des données de tests (des nouveaux clients ou articles ...)


## TP 1 : CSV
Un fichier CSV est un fichier texte "à plat". Chaque ligne correspond à une ligne du tableau, chaque colonne est séparée par des ";"
Le contenu d'une cellule peut être entourée de double quotes
* Exporter la liste des clients au format CSV (colonnes: nom, prénom) => accessible via le lien http://localhost:8080/export/clients/csv
  (Déja développé)
* Ajouter une nouvelle colonne : age du client ( fichier exemple /exports-attendus/02-clients-csv-avec-age.csv )
* Exporter la liste des articles (Fichier exemple exports-attendus/03-articles.csv)



**Bonus** : fiabiliser l'export ajouter la colonne description sur l'article (autorisant les caractères spéciaux ; et saut de ligne) 
et réaliser l'export du stock article en gérant ces caractères spéciaux.

## TP2 : XLSX
Faire de même avec un export au format xlsx. Cela correspond au format Excel permettant des fichiers plus complexe (onglets, fusion de cellule, styles, formules, ...)
Exemple de code pour réaliser un fichier xslx
```
Workbook workbook = new XSSFWorkbook();
Sheet sheet = workbook.createSheet("Clients");
Row headerRow = sheet.createRow(0);
Cell cellPrenom = headerRow.createCell(0);
cellPrenom.setCellValue("Prénom");
workbook.write(fileOutputStream);
workbook.close();
```
Exercice:
* Exporter le fichier XLSX de tous les clients (colonnes: nom, prénom) => accessible via le lien http://localhost:8080/export/clients/xlsx (déja fait)
* Créer un export XLSX multi onglet : un pour le client sélectionné, et créer un onglet par factures correspondant au client
    chaque onglet contiendra le détail de la facture (colonnes : désignation, quantité, prixUnitaire, prixLigne) et rajouter le prix total de la facture en bas (utilise un colspan) (Fichier exemple exports-attendus/05-factures-excel.xlsx)
     

## TP3 : PDF (iText)
* Télécharger une facture accessible via le lien suivant : http://localhost:8080/export/factures/16/pdf
* Créer le PDF d'une facture 
  * entete : Nom prénom du client , logo de l'IPI se trouvant à la racine du projet (logo-ipi.jpg) 
  * Ajouter le numéro de facture centré et en gras
  * tableau contenant le détail de la facture, prix total de la facture en dessous, sur chaque ligne ajouter une colonne indiquant la somme pric unitaire * quantité 

    

## PDF (jasperreport) (BONUS on verra ensemble)
Jasperreport est un outil de templating pour générer des PDF (et autres)

créer un nouveau report
utiliser une datasource XML
* Créer un service de génération de PDF via BIRT : le service utilise un template jasperreport et fournit un dataset SQL pour créer le PDF.


