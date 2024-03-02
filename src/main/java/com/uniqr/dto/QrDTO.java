package com.uniqr.dto;

import com.uniqr.model.QR;
import com.uniqr.model.QrChecks;
import com.uniqr.model.Session;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QrDTO {
    private String id;
    private Date created;
    private List<Date> dates;

    public static QrDTO mapToQrDTO(QR qr) {
        List<Date> dates = qr.getDates().stream().map(QrChecks::getDate).toList();

        return new QrDTO(
                qr.getId(),
                qr.getCreated(),
                dates
        );
    }
}
