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
@Table(name = "sessions")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    @Id
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "created")
    private Date created;
    @Column(name = "amount_qrs")
    private Long amountQRs;
    @Column(name = "description")
    private String desc;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH,
            CascadeType.REFRESH, CascadeType.MERGE},
            mappedBy = "session")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<QR> qrs = new ArrayList<>();

    public Session(String name ,Long amount, Date date, String desc) {
        this.name = name;
        this.amountQRs = amount;
        this.created = date;
        this.id = UUID.randomUUID().toString();
        this.desc = desc;
    }
}
