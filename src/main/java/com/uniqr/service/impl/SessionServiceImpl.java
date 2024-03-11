package com.uniqr.service.impl;

import com.uniqr.dto.SessionDTO;
import com.uniqr.dto.SessionFullDTO;
import com.uniqr.dto.SessionShortDTO;
import com.uniqr.model.QR;
import com.uniqr.model.Session;
import com.uniqr.repository.SessionRepository;
import com.uniqr.service.SessionService;
import com.uniqr.service.exception.SessionFetchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;

    @Autowired
    public SessionServiceImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public List<SessionShortDTO> getListSessions() {
        return sessionRepository.findAll().stream().map(SessionShortDTO::mapToShortDTO).toList();
    }

    @Override
    public SessionFullDTO createSession(SessionDTO sessionDTO) {
        Session session = SessionDTO.mapToSession(sessionDTO);
        List<QR> generateQRs = generateQRs(session.getId(), sessionDTO.getAmount());
        session.setQrs(generateQRs);

        sessionRepository.save(session);
        return SessionFullDTO.mapToSessionDTO(session);
    }

    @Override
    public Session getSession(String id) {
        return sessionRepository.findById(id).orElseThrow(() ->
                new SessionFetchException(String.format("Session with id: %s not found", id))
        );
    }

    @Override
    public SessionDTO getSessionDTO(String id) {
        Session session = sessionRepository.findById(id).orElseThrow(() ->
                new SessionFetchException(String.format("Session with id: %s not found", id))
        );
        return SessionDTO.mapToSessionDTO(session);
    }

        @Override
    public Session saveSession(Session session) {
        return sessionRepository.save(session);
    }

    private List<QR> generateQRs(String uuid, Long amount) {
        List<QR> result = new ArrayList<>();
        for(Long i = 0L; i < amount; i++) {
            result.add(new QR(uuid));
        }
        return result;
    }
}
