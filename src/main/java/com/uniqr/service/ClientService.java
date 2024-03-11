package com.uniqr.service;

import com.uniqr.dto.*;
import com.uniqr.model.Session;

import java.util.List;

public interface ClientService {
    ClientDTO createClient(String client);
    ClientDTO getClientByDomain(String client);
    SessionFullDTO createSession(String clientId , Session session);
    List<ClientDTO> getClients();
    List<SessionShortDTO> getListSessionsByClient(String clientId);
}
