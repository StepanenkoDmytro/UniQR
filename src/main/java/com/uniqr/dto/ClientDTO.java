package com.uniqr.dto;

import com.uniqr.model.Client;
import com.uniqr.model.Session;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private String id;
    private String domainClient;
    private List<SessionDTO> sessions;

    public static Client mapToClient(ClientDTO clientDTO) {
//        List<Session> collect = clientDTO.getSessions().stream().map(SessionDTO::mapToSession).toList();

        return new Client(
                clientDTO.getDomainClient()
        );
    }

    public static ClientDTO mapToClientDTO(Client client) {
        List<SessionDTO> collect;
        if(client.getSessions() != null) {
            collect = client.getSessions().stream().map(SessionDTO::mapToSessionDTO).toList();
        } else {
            collect = new ArrayList<>();
        }
        return new ClientDTO(
                client.getId(),
                client.getDomainClient(),
                collect
        );
    }
}
