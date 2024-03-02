package com.uniqr.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "session")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dataTime")
    private Date dataTime;

    @Column(name = "amount_qrs")
    private Long amountQRs;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH,
            CascadeType.REFRESH, CascadeType.MERGE},
            mappedBy = "qr")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<QR> qrs = new ArrayList<>();

    public Session(Long amount, Date date) {
        this.amountQRs = amount;
        this.dataTime = date;
    }

    public Session(Long amount, Date date, List<QR> qrs) {
        this.amountQRs = amount;
        this.dataTime = date;

        this.qrs = qrs;
    }

//    public void addQr(QR qr) {
//        if(qrs == null) {
//            qrs = new ArrayList<>();
//        }
//        qrs.add(qr);
//        qr.setSession(this);
//    }

    public void addQRs(List<QR> qrs) {
        if (this.qrs == null) {
            this.qrs = new ArrayList<>();
        }
        for (QR qr : qrs) {
            qr.setSession(this.id); // Встановлення сесії для кожного QR
            this.qrs.add(qr);
            System.out.println(qr);// Додавання QR до списку сесії
        }
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setDataTime(Date dataTime) {
        this.dataTime = dataTime;
    }

    public void setAmountQRs(Long amountQRs) {
        this.amountQRs = amountQRs;
    }

//    public void setQrs(List<QR> qrs) {
//        qrs.forEach(this::addQr);
//        this.qrs = qrs;
//    }
}
