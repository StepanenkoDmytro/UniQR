package com.uniqr.service;

import com.uniqr.dto.SessionDTO;
import com.uniqr.model.Session;

import java.util.List;

public interface SessionService {
    List<SessionDTO> getListSessions();
    Session createSession(SessionDTO session);
    Session getSession(Long id);
    Session saveSession(Session session);
    SessionDTO getSessionDTO(Long id);
}
