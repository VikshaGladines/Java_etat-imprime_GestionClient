package com.example.printstate.service.mapper;

import com.example.printstate.dto.ClientDto;
import com.example.printstate.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public ClientDto clientDto(Client client) {
        return new ClientDto(client.getId(), client.getNom(), client.getPrenom());
    }

}
