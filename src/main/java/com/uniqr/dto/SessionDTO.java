package com.uniqr.dto;

import com.uniqr.model.QR;
import com.uniqr.model.Session;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionDTO {
    private Long id;
    private Long amount;
    private List<String> qrDTOs;

    public static Session mapToSession(SessionDTO sessionDTO) {
        Date date = new Date();


//        qrs.forEach(qr -> qr.setSession(session));
//        session.addQRs(qrs);

        return new Session(
                sessionDTO.getAmount(),
                date
        );
    }

    public static SessionDTO mapToSessionDTO(Session session) {
        List<String> collect = session.getQrs().stream().map(qr -> qr.getQr()).collect(Collectors.toList());
//        qrs.forEach(qr -> qr.setSession(session));
//        session.addQRs(qrs);

        return new SessionDTO(
                session.getId(),
                session.getAmountQRs(),
                collect
        );
    }
}
