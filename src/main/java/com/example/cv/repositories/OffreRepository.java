package com.example.cv.repositories;

import com.example.cv.entities.Offre;
import com.example.cv.sercurityglobal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OffreRepository extends JpaRepository<Offre, Long> {
    List<Offre> findByUser(User user);

    @Query("SELECT o FROM Offre o WHERE " +
            "(:title IS NULL OR o.titre LIKE %:title%) AND " +
            "(:cityName IS NULL OR o.country.countryName LIKE %:cityName%)")
    List<Offre> searchOffers(@Param("title") String title, @Param("cityName") String cityName);
}
