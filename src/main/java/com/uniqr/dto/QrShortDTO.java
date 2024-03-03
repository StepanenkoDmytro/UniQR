package com.uniqr.dto;

import com.uniqr.model.QR;
import com.uniqr.model.QrChecks;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QrShortDTO {
    private String id;
    private Date created;

    public static QrShortDTO mapToQrShortDTO(QR qr) {

        return new QrShortDTO(
                qr.getId(),
                qr.getCreated()
        );
    }
}
