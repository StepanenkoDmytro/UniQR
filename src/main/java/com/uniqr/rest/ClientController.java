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
    public ResponseEntity<String> createClient(@RequestBody Map<String, String> requestBody) {
        String domain = requestBody.get("domain");

        if (domain == null || domain.isEmpty()) {
            return ResponseEntity.badRequest().body("Missing domain parameter");
        }

        ClientDTO existingClient = clientService.getClientByDomain(domain);
        if (existingClient != null) {
            return ResponseEntity.ok(existingClient.getId());
        }

        ClientDTO newClient = clientService.createClient(domain);
        return ResponseEntity.ok(newClient.getId());
    }

    @GetMapping("/getClients")
    public ResponseEntity<?> getClients() {
        return ResponseEntity.ok(clientService.getClients());
    }

    @PostMapping("/{clientID}/createSession")
    public ResponseEntity<?> crateSession(@PathVariable String clientID,
                                                        @RequestParam(value = "image", required = false) MultipartFile file,
                                                        @RequestParam Map<String, String> sessionRequestBody) {
        String name = sessionRequestBody.get("name");
        String qrAmount = sessionRequestBody.get("qrAmount");

        if(name == null || qrAmount == null) {
            return ResponseEntity.badRequest().body("Parameter name or qrAmount can not be null");
        }
        Session session = Session.fromMap(sessionRequestBody);

        SessionFullDTO sessionDto = clientService.createSession(clientID, session, file);
        return ResponseEntity.ok(sessionDto);
    }

    //TODO: добавить Пагинацию
    @GetMapping("/{clientID}/getSessions")
    public ResponseEntity<List<SessionShortDTO>> getSessions(@PathVariable String clientID) {
        return ResponseEntity.ok(clientService.getListSessionsByClient(clientID));
    }

    @GetMapping("/{clientID}/getSessions/{id}")
    public ResponseEntity<SessionDTO> getSessionsById(@PathVariable String clientID, @PathVariable String id) {
        return ResponseEntity.ok(sessionService.getSessionDTO(id));
    }
}
