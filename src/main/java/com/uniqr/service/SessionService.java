package com.uniqr.service;

import com.uniqr.dto.SessionDTO;
import com.uniqr.dto.SessionFullDTO;
import com.uniqr.dto.SessionShortDTO;
import com.uniqr.model.Session;

import java.util.List;

public interface SessionService {
    List<SessionShortDTO> getListSessions();
    SessionFullDTO createSession(SessionDTO session);
    Session getSession(String id);
    Session saveSession(Session session);
    SessionDTO getSessionDTO(String id);
}
