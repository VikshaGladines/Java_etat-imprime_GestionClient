package com.example.printstate.service.impl;

import com.example.printstate.dto.ClientDto;
import com.example.printstate.repository.ClientRepository;
import com.example.printstate.service.ClientService;
import com.example.printstate.service.mapper.ClientMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service contenant les actions métiers liées aux clients.
 */
@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;
    private ClientMapper clientMapper;

    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public List<ClientDto> findAllClients() {
        return clientRepository.findAll().stream().map(article -> clientMapper.clientDto(article)).collect(Collectors.toList());
    }
}
