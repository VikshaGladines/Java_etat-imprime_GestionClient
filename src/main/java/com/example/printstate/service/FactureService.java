package com.example.printstate.service;

import com.example.printstate.dto.FactureDto;

import java.util.List;

public interface FactureService {
    List<FactureDto> findAllFactures();

    FactureDto findById(Long id);
}
