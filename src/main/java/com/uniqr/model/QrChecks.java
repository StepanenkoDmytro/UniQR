package com.uniqr.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "qr_checks")
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QrChecks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "qr_id")
    private String qr;
    @Column(name = "date")
    private Date date;

    public QrChecks(Date date) {
        this.date = date;
    }
}
