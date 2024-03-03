package com.uniqr.dto;

import com.uniqr.model.QR;
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
    private Date crated;
    private String desc;
    private List<QrShortDTO> qrs;

    public static SessionFullDTO mapToSessionDTO(Session session) {
        List<QrShortDTO> qrsList = session.getQrs().stream().map(QrShortDTO::mapToQrShortDTO).toList();

        return new SessionFullDTO(
                session.getId(),
                session.getName(),
                session.getAmountQRs(),
                session.getCreated(),
                session.getDesc(),
                qrsList
        );
    }
}
