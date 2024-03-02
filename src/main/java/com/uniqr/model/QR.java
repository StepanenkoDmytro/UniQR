package com.uniqr.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "qrs")
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
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH,
            CascadeType.REFRESH, CascadeType.MERGE},
            mappedBy = "qr")
    private List<QrChecks> dates;

    public QR(String session) {
        this.session = session;
        this.created = new Date();
        this.id = UUID.randomUUID().toString();
    }

    public QR() {
        this.created = new Date();
        this.id = UUID.randomUUID().toString();
    }

    public void addCheckDate(Date date) {
        if(dates == null) {
            dates = new ArrayList<>();
        }
        QrChecks qrChecks = new QrChecks(date);
        qrChecks.setQr(this.id);
        dates.add(qrChecks);
    }
}
