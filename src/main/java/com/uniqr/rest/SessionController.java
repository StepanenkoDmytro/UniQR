package com.uniqr.rest;

import com.uniqr.dto.SessionDTO;
import com.uniqr.dto.SessionFullDTO;
import com.uniqr.dto.SessionShortDTO;
import com.uniqr.model.Session;
import com.uniqr.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<SessionFullDTO> createSession(@RequestBody SessionDTO sessionDTo) {
        SessionFullDTO session = sessionService.createSession(sessionDTo);
        return ResponseEntity.ok(session);
    }

    //TODO: добавить Пагинацию
    @GetMapping("/getSessions")
    public ResponseEntity<List<SessionShortDTO>> getSessions() {
        return ResponseEntity.ok(sessionService.getListSessions());
    }

    @GetMapping("/getSessions/{id}")
    public ResponseEntity<SessionDTO> getSessionsById(@PathVariable String id) {
        return ResponseEntity.ok(sessionService.getSessionDTO(id));
    }
}
