package com.uniqr.rest;

import com.uniqr.dto.*;
import com.uniqr.model.Session;
import com.uniqr.service.ClientService;
import com.uniqr.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/clients")
@CrossOrigin
public class ClientController {
    private final ClientService clientService;
    private final SessionService sessionService;

    @Autowired
    public ClientController(ClientService clientService, SessionService sessionService) {
        this.clientService = clientService;
        this.sessionService = sessionService;
    }

    @PostMapping("/createClient")
    public ResponseEntity<?> createClient(@RequestBody Map<String, String> requestBody) {
        String domain = requestBody.get("domain");

        if (domain == null || domain.isEmpty()) {
            // Обробка помилки, якщо 'domain' не було передано
            return ResponseEntity.badRequest().body("Missing 'domain' parameter");
        }

        ClientDTO existingClient = clientService.getClientByDomain(domain);
        if (existingClient != null) {
            // Якщо домен вже існує, повертаємо його дані
            return ResponseEntity.ok(existingClient);
        }

        // Якщо домен не існує, створюємо новий запис
        ClientDTO newClient = clientService.createClient(domain);
        return ResponseEntity.ok(newClient.getId());
    }

    @GetMapping("/getClients")
    public ResponseEntity<?> getClients() {
        return ResponseEntity.ok(clientService.getClients());
    }

    @PostMapping("/{clientID}/createSession")
    public ResponseEntity<SessionFullDTO> createSession(@PathVariable String clientID,
                                                        @RequestParam(value = "image", required = false) MultipartFile image,
                                                        @RequestParam Map<String, String> createSession) {
        SessionFullDTO session = clientService.createSession(clientID, Session.fromMap(createSession));
        return ResponseEntity.ok(session);
    }

    //TODO: добавить Пагинацию
    @GetMapping("/{clientID}/getSessions")
    public ResponseEntity<List<SessionShortDTO>> getSessions(@PathVariable String clientID) {
        return ResponseEntity.ok(clientService.getListSessionsByClient(clientID));
    }

    @GetMapping("/{clientID}/getSessions/{id}")
    public ResponseEntity<SessionDTO> getSessionsById(@PathVariable Long clientID, @PathVariable String id) {
        return ResponseEntity.ok(sessionService.getSessionDTO(id));
    }
}
