package com.uniqr.dto;

import com.uniqr.model.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientCreateDTO {
    private String domainClient;

    public static Client mapToClient(ClientCreateDTO clientCreateDTO) {
        return new Client(
                clientCreateDTO.getDomainClient()
        );
    }
}
