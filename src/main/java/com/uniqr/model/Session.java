package com.uniqr.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "session")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    @Id
    private String id;
    @Column(name = "dataTime")
    private Date dataTime;
    @Column(name = "amount_qrs")
    private Long amountQRs;
    @Column(name = "description")
    private String desc;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH,
            CascadeType.REFRESH, CascadeType.MERGE},
            mappedBy = "session")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<QR> qrs = new ArrayList<>();

    public Session(Long amount, Date date, String desc) {
        this.amountQRs = amount;
        this.dataTime = date;
        this.id = UUID.randomUUID().toString();
        this.desc = desc;
    }

    public Session(Long amount, Date date, List<QR> qrs) {
        this.amountQRs = amount;
        this.dataTime = date;

        this.qrs = qrs;
    }
}
