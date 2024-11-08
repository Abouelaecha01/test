package com.example.cv.repositories;

import com.example.cv.entities.City;
import com.example.cv.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City,Long> {
}
