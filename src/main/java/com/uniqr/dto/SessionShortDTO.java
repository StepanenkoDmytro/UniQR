package com.uniqr.dto;

import com.uniqr.model.Session;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionShortDTO {
    private String id;
    private String name;
    private Long amount;
    private Date created;

    public static SessionShortDTO mapToShortDTO(Session session) {
        return new SessionShortDTO(
                session.getId(),
                session.getName(),
                session.getAmountQRs(),
                session.getCreated()
        );
    }
}
