package com.example.cv.repositories;


import com.example.cv.entities.Cv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;

// import java.util.List;

public interface CvRepository extends JpaRepository<Cv, Long> {
    Optional<Cv> findByEmail(String email);

    @Query("SELECT cvs.cv FROM CvSkill cvs WHERE cvs.skill.skillID = :skillId")
    List<Cv> findCvsBySkillId(@Param("skillId") Long skillId);

}
