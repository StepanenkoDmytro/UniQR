package com.uniqr.repository;

import com.uniqr.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {
    Client findBydomainClient(String domain);
}
