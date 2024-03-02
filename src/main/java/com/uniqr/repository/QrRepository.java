package com.uniqr.repository;

import com.uniqr.model.QR;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QrRepository extends JpaRepository<QR, String> {
}
