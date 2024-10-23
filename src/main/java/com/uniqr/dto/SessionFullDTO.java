package com.uniqr.dto;

import com.uniqr.model.Session;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionFullDTO {
    private String id;
    private String name;
    private Long amount;
    private Date created;
    private String desc;
    private String image;
    private String clientDomain;
    private List<QrShortDTO> qrs;

    public static SessionFullDTO mapToSessionDTO(Session session) {
        List<QrShortDTO> qrsList = session.getQrs().stream().map(QrShortDTO::mapToQrShortDTO).toList();
        String imageURL = null;
        if(session.getImage() != null) {
            imageURL = "https://pegazzo.online:8081/api/v1/images/" + session.getImage().getId();
        }

        return new SessionFullDTO(
                session.getId(),
                session.getName(),
                session.getAmountQRs(),
                session.getCreated(),
                session.getDesc(),
                imageURL,
                session.getClientDomain(),
                qrsList
        );
    }
}
