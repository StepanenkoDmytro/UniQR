package com.uniqr.rest;

import com.uniqr.dto.SessionDTO;
import com.uniqr.model.Session;
import com.uniqr.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/session")
@CrossOrigin
public class SessionController {

    private final SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping("/createSession")
    public ResponseEntity<?> createSession(@RequestBody SessionDTO sessionDTo) {

        Session session = sessionService.createSession(sessionDTo);

        return ResponseEntity.ok(session);
    }

    @GetMapping("/getSessions")
    public ResponseEntity getSessions() {
        return ResponseEntity.ok(sessionService.getListSessions());
    }

    @GetMapping("/getSessions/{id}")
    public ResponseEntity getSessionsById(@PathVariable String id) {
        return ResponseEntity.ok(sessionService.getSessionDTO(id));
    }
}
