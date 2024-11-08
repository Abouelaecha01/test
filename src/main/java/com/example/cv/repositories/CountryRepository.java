package com.example.cv.repositories;

import com.example.cv.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository <Country,Long> {
}
