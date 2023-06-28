package com.example.printstate.service.export;

import com.example.printstate.entity.Client;
import com.example.printstate.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.List;

@Service
public class ClientExportCVSService {

    private final ClientRepository clientRepository;

    public ClientExportCVSService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void export(PrintWriter writer) {
        writer.println("Nom;Prénom;Age");
        List<Client> clients = clientRepository.findAll();
        for (Client client : clients) {
            String line = client.getNom() + ";" + client.getPrenom() + ";" + client.getAge() ;
            writer.println(line);
        }

    }


}
