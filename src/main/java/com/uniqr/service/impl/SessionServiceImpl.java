package com.uniqr.service.impl;

import com.uniqr.dto.SessionDTO;
import com.uniqr.model.QR;
import com.uniqr.model.Session;
import com.uniqr.repository.SessionRepository;
import com.uniqr.service.SessionService;
import com.uniqr.service.exception.SessionFetchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;

    @Autowired
    public SessionServiceImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public List<SessionDTO> getListSessions() {
        return sessionRepository.findAll().stream().map(SessionDTO::mapToSessionDTO).collect(Collectors.toList());
    }

    @Override
    public Session createSession(SessionDTO sessionDTO) {
        Session session = SessionDTO.mapToSession(sessionDTO);
        List<QR> generateQRs = generateQRs(session.getId(), sessionDTO.getAmount());
        session.setQrs(generateQRs);

        sessionRepository.save(session);
        return session;
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
