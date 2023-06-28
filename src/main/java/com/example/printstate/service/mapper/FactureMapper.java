package com.example.printstate.service.mapper;

import com.example.printstate.dto.ArticleDto;
import com.example.printstate.dto.ClientDto;
import com.example.printstate.dto.FactureDto;
import com.example.printstate.dto.LigneFactureDto;
import com.example.printstate.entity.Article;
import com.example.printstate.entity.Facture;
import com.example.printstate.entity.LigneFacture;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class FactureMapper {

    private final ClientMapper clientMapper;

    public FactureMapper(ClientMapper clientMapper) {
        this.clientMapper = clientMapper;
    }

    public FactureDto factureDto(Facture f) {
        ClientDto clientDto = clientMapper.clientDto(f.getClient());
        List<LigneFactureDto> ligneFactureDtos = f.getLigneFactures()
                .stream()
                .map(lf -> ligneFactureDto(lf))
                .collect(toList());
        return new FactureDto(f.getId(), clientDto, ligneFactureDtos);
    }

    private LigneFactureDto ligneFactureDto(LigneFacture lf) {
        Article article = lf.getArticle();
        ArticleDto articleDto = new ArticleDto(article.getId(), article.getLibelle(), article.getPrix(), article.getStock());
        return new LigneFactureDto(articleDto, lf.getQuantite());
    }


}
