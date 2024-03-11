package com.uniqr.rest;

import com.uniqr.dto.SessionDTO;
import com.uniqr.dto.SessionFullDTO;
import com.uniqr.dto.SessionShortDTO;
import com.uniqr.service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Enumeration;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/testFormData")
    public ResponseEntity<?> test(@RequestParam(value = "image", required = false) MultipartFile image,
                                  @RequestParam Map<String, String> createSession) {
        if(image != null) {
            System.out.println("object" + image.getOriginalFilename());
        }
        createSession.forEach((key, value) -> System.out.println(key + ":" + value));
        return ResponseEntity.ok(HttpStatus.OK);
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
