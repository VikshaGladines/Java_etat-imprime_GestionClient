package com.example.printstate.service.impl;

import com.example.printstate.dto.FactureDto;
import com.example.printstate.entity.Facture;
import com.example.printstate.repository.FactureRepository;
import com.example.printstate.service.FactureService;
import com.example.printstate.service.mapper.FactureMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Service contenant les actions métiers liées aux factures.
 */
@Service
@Transactional
public class FactureServiceImpl implements FactureService {

    private FactureRepository factureRepository;
    private FactureMapper factureMapper;

    public FactureServiceImpl(FactureRepository factureRepository, FactureMapper factureMapper) {
        this.factureRepository = factureRepository;
        this.factureMapper = factureMapper;
    }

    @Override
    public List<FactureDto> findAllFactures() {
        List<Facture> factures = factureRepository.findAll();
        // Transformation d'une liste de Facture en liste de FactureDto
        return factures.stream().map(facture -> factureMapper.factureDto(facture)).collect(toList());
    }

    @Override
    public FactureDto findById(Long id) {
        Facture facture = factureRepository.findById(id).get();
        // Transformation d'une Facture en FactureDto
        return factureMapper.factureDto(facture);
    }

}
