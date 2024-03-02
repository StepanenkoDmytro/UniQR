package com.uniqr.rest;

import com.uniqr.dto.QrDTO;
import com.uniqr.service.QRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/checkQR")
@CrossOrigin
public class QRController {
    private final QRService qrService;

    @Autowired
    public QRController(QRService qrService) {
        this.qrService = qrService;
    }

    @GetMapping("{id}")
    public ResponseEntity<?> checkQR(@PathVariable String id) {
        QrDTO qrDTO = qrService.checkQRById(id);
        return ResponseEntity.ok(qrDTO);
    }
}
