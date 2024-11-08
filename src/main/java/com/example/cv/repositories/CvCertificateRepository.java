package com.example.cv.repositories;

import com.example.cv.entities.CvCertificate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CvCertificateRepository extends JpaRepository<CvCertificate, Long> {
}
