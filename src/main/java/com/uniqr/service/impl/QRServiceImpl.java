package com.uniqr.service.impl;

import com.uniqr.dto.QrDTO;
import com.uniqr.model.QR;
import com.uniqr.repository.QrRepository;
import com.uniqr.service.QRService;
import com.uniqr.service.exception.QrFetchException;
import com.uniqr.service.exception.SessionFetchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class QRServiceImpl implements QRService {
    private final QrRepository qrRepository;

    @Autowired
    public QRServiceImpl(QrRepository qrRepository) {
        this.qrRepository = qrRepository;
    }

    @Override
    public QrDTO checkQRById(String id) throws QrFetchException {
        QR qr = qrRepository.findById(id).orElseThrow(() ->
                new QrFetchException(String.format("QR with id: %s not found", id))
        );

        QrDTO response = QrDTO.mapToQrDTO(qr);
        Date currentDate = new Date();
        qr.addCheckDate(currentDate);
        qrRepository.save(qr);

        return response;
    }
}
