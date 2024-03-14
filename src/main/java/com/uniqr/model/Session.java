package com.uniqr.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.*;

@Entity
@Table(name = "sessions")
@Data
@Getter
@Setter
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
    @Column(name = "client_id")
    private String client;
    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private Image image;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH,
            CascadeType.REFRESH, CascadeType.MERGE},
            mappedBy = "session")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<QR> qrs = new ArrayList<>();

    public Session() {
        this.id = UUID.randomUUID().toString();
    }

    public Session(String name ,Long amount, Date date, String desc) {
        this.name = name;
        this.amountQRs = amount;
        this.created = date;
        this.id = UUID.randomUUID().toString();
        this.desc = desc;
    }

    public static Session fromMap(Map<String, String> map) {
        Session session = new Session();
        session.setName(map.get("name"));
        session.setAmountQRs(Long.parseLong(map.get("qrAmount")));
        session.setCreated(new Date());
        return session;
    }
}
