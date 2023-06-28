package com.example.printstate.service;

import com.example.printstate.dto.ClientDto;

import java.util.List;

public interface ClientService {
    List<ClientDto> findAllClients();
}
