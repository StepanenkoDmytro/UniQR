package com.uniqr.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "clients")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    private String id;
    @Column(name = "domain_client")
    private String domainClient;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH,
            CascadeType.REFRESH, CascadeType.MERGE},
            mappedBy = "client")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Session> sessions;

    public Client(String domainClient) {
        this.id = UUID.randomUUID().toString();
        this.domainClient = domainClient;
    }

    public void addSession(Session session) {
        if(sessions == null) {
            sessions = new ArrayList<>();
        }
        session.setClient(this.id);
        session.setClientDomain(this.domainClient);
        sessions.add(session);
    }
}
