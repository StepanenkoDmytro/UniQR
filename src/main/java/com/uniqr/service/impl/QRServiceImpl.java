package com.uniqr.service.impl;

import com.uniqr.dto.QrDTO;
import com.uniqr.dto.SessionDTO;
import com.uniqr.model.QR;
import com.uniqr.model.Session;
import com.uniqr.repository.QrRepository;
import com.uniqr.service.QRService;
import com.uniqr.service.SessionService;
import com.uniqr.service.exception.QrFetchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class QRServiceImpl implements QRService {
    private final QrRepository qrRepository;
    private final SessionService sessionService;

    @Autowired
    public QRServiceImpl(QrRepository qrRepository, SessionService sessionService) {
        this.qrRepository = qrRepository;
        this.sessionService = sessionService;
    }

    @Override
    public QrDTO checkQRById(String id) throws QrFetchException {
        QR qr = qrRepository.findById(id).orElseThrow(() ->
                new QrFetchException(String.format("QR with id: %s not found", id))
        );
        SessionDTO sessionDTO = sessionService.getSessionDTO(qr.getSession());

        QrDTO response = QrDTO.mapToQrDTO(qr, sessionDTO);

        Date currentDate = new Date();
        qr.addCheckDate(currentDate);
        qrRepository.save(qr);

        return response;
    }
}
