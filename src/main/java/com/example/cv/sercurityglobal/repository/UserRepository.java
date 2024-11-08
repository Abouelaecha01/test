package com.example.cv.sercurityglobal.repository;

import com.example.cv.sercurityglobal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUserNameAndEmail(String nom, String email);

    @Query("SELECT u FROM User u WHERE u.role.nomRole = :roleName")
    List<User> findAllByRoleName(@Param("roleName") String roleName);

}
