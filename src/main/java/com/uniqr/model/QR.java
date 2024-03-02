package com.uniqr.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "QRs")
@Data
@Setter
@Getter
@AllArgsConstructor
public class QR {
    @Id
    private String id;
    @Column(name = "session_id")
    private String session;
    @Column(name = "created")
    private Date created;
    @Column(name = "dates", columnDefinition = "json")
    private String dates;

    public QR(String session) {
        this.session = session;
        this.created = new Date();
        this.id = UUID.randomUUID().toString();
    }

    public QR() {
        this.created = new Date();
        this.id = UUID.randomUUID().toString();
    }
}
