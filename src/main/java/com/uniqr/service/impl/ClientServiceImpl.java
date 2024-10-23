package com.uniqr.service.impl;

import com.uniqr.dto.*;
import com.uniqr.model.Client;
import com.uniqr.model.Image;
import com.uniqr.model.QR;
import com.uniqr.model.Session;
import com.uniqr.repository.ClientRepository;
import com.uniqr.repository.SessionRepository;
import com.uniqr.service.ClientService;
import com.uniqr.service.exception.ClientFetchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final SessionRepository sessionRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, SessionRepository sessionRepository) {
        this.clientRepository = clientRepository;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public ClientDTO createClient(String domain) {
        Client save = clientRepository.save(new Client(domain));
        return ClientDTO.mapToClientDTO(save);
    }

    @Override
    public ClientDTO getClientByDomain(String client) {
        Client bydomainClient = clientRepository.findBydomainClient(client);
        if(bydomainClient != null) {
            return ClientDTO.mapToClientDTO(bydomainClient);
        }
        return null;
    }

    @Override
    public SessionFullDTO createSession(String clientId, Session session, MultipartFile file) {
        Optional<Client> clientById = clientRepository.findById(clientId);

        if(clientById.isEmpty()) {
            throw new ClientFetchException(String.format("Client with id: %s not found", clientId));
        }
        Client client = clientById.get();

        List<QR> generateQRs = generateQRs(session.getId(), session.getAmountQRs());
        session.setQrs(generateQRs);

        if (file != null && file.getSize() != 0) {
            try {
                Image image = toImageEntity(file);
                session.setImage(image);
            } catch (IOException exp) {
                //TODO: add logger
                System.out.println("Some trouble with image file: " + exp);
            }
        }
        client.addSession(session);

        clientRepository.save(client);

        return SessionFullDTO.mapToSessionDTO(session);
    }

    @Override
    public List<ClientDTO> getClients() {
        return clientRepository.findAll().stream().map(ClientDTO::mapToClientDTO).toList();
    }

    @Override
    public List<SessionShortDTO> getListSessionsByClient(String clientId) {
        Optional<Client> clientById = clientRepository.findById(clientId);
        if(clientById.isEmpty()) {
            throw new ClientFetchException(String.format("Client with id: %s not found", clientId));
        }
        return clientById.get().getSessions().stream().map(SessionShortDTO::mapToShortDTO).toList();
    }



    private List<QR> generateQRs(String uuid, Long amount) {
        List<QR> result = new ArrayList<>();
        for(Long i = 0L; i < amount; i++) {
            result.add(new QR(uuid));
        }
        return result;
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setSize(file.getSize());
        image.setOriginFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setBytes(file.getBytes());
        return image;
    }
}
