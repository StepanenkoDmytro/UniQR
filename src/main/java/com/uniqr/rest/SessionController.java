package com.uniqr.rest;

import com.uniqr.dto.SessionDTO;
import com.uniqr.dto.Test;
import com.uniqr.model.QR;
import com.uniqr.model.Session;
import com.uniqr.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/session")
public class SessionController {

    private final SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping("/createSession")
    public ResponseEntity<?> createSession(@RequestBody SessionDTO sessionDTo) {
        List<String> qrDTOsList = sessionDTo.getQrDTOs();
        if(qrDTOsList.size() != sessionDTo.getAmount()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    "The number of QR codes does not match the specified amount."
            );
        }
        Session session = sessionService.createSession(sessionDTo);
        System.out.println("controller:" + session);


        return ResponseEntity.ok(session);
    }

    @PostMapping("/saveQRs/{id}")
    public ResponseEntity<?> savedQRs(@PathVariable Long id, @RequestBody Test qrsDto) {
        Session session = sessionService.getSession(id);
        List<QR> qrs = qrsDto.getQrDTOs().stream()
                .map(qrDTO -> new QR(qrDTO))
                .collect(Collectors.toList());
        System.out.println("saveQRs:" + qrs);

        // Додайте QR до сесії та встановіть сесію для кожного QR
        session.addQRs(qrs);

        // Збережіть сесію зі зв'язаними QR-кодами
        sessionService.saveSession(session);
//        Session savedSession =
        System.out.println("saveQRs:" + session);
        return ResponseEntity.ok(SessionDTO.mapToSessionDTO(session));
    }

    @GetMapping("/getSessions")
    public ResponseEntity getSessions() {
        return ResponseEntity.ok(sessionService.getListSessions());
    }

    @GetMapping("/getSessions/{id}")
    public ResponseEntity getSessionsById(@PathVariable Long id) {
        return ResponseEntity.ok(sessionService.getSessionDTO(id));
    }
}
