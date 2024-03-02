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
public class SessionDTO {
    private String id;
    private Long amount;
    private Date crated;
    private List<String> qrDTOs;

    public static Session mapToSession(SessionDTO sessionDTO) {
        Date date = new Date();

        return new Session(
                sessionDTO.getAmount(),
                date
        );
    }

    public static SessionDTO mapToSessionDTO(Session session) {
        List<String> collect = session.getQrs().stream().map(QR::getId).toList();

        return new SessionDTO(
                session.getId(),
                session.getAmountQRs(),
                session.getDataTime(),
                collect
        );
    }
}
