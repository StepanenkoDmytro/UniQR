package com.uniqr.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "QRs")
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QR {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "qr")
    private String qr;

    @Column(name = "session_id")
    private Long session;

    public QR(String qr) {
        this.qr = qr;
    }

//    public QR(String qr, Session session) {
//        this.qr = qr;
//        session.addQr(this);
//        this.session = session;
//    }
}
