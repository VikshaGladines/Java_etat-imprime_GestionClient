package com.example.printstate.controller.rest;

import com.example.printstate.dto.FactureDto;
import com.example.printstate.service.FactureService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller pour exposition d'api REST des factures.
 */
@RestController
@RequestMapping("rest/factures")
public class FactureRestController {

    private final FactureService factureService;

    public FactureRestController(FactureService factureService) {
        this.factureService = factureService;
    }

    /**
     * Exposition d'une api
     *
     * @return la liste des factures au format JSON.
     */
    @GetMapping
    public List<FactureDto> getFactures() {
        return factureService.findAllFactures();
    }

    /**
     * Exposition d'une api
     *
     * @return une factures au format JSON.
     */
    @GetMapping("{id}")
    public FactureDto getFacture(@PathVariable Long id) {
        return factureService.findById(id);
    }


}
