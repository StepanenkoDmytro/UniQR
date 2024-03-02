package com.uniqr.service.impl;

import com.uniqr.dto.SessionDTO;
import com.uniqr.model.QR;
import com.uniqr.model.Session;
import com.uniqr.repository.SessionRepository;
import com.uniqr.service.SessionService;
import com.uniqr.service.exception.SessionFetchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        // Створіть об'єкт Session з DTO
        Session session = SessionDTO.mapToSession(sessionDTO);
        sessionRepository.save(session);
//        System.out.println(savedSession);

        // Створіть об'єкти QR з DTO

//        System.out.println(savedSession);
        // Поверніть збережену сесію
        return session;
    }

    @Override
    public Session getSession(Long id) {
        return sessionRepository.findById(id).orElseThrow(() ->
                new SessionFetchException(String.format("Session with id: %s not found", id))
        );
    }

    @Override
    public SessionDTO getSessionDTO(Long id) {
        Session session = sessionRepository.findById(id).orElseThrow(() ->
                new SessionFetchException(String.format("Session with id: %s not found", id))
        );
        return SessionDTO.mapToSessionDTO(session);
    }

        @Override
    public Session saveSession(Session session) {
        return sessionRepository.save(session);
    }
}
