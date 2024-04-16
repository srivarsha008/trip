package com.java.trips.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.java.trips.Model.Login;

import java.util.List;

@Repository
public interface LoginRepo extends JpaRepository<Login, Integer> {
    // Custom query to retrieve login details by name
    @Query("SELECT l FROM Login l WHERE l.name = :name")
    List<Login> findByName(@Param("name") String name);

    // Custom query to retrieve login details by username
    @Query("SELECT l FROM Login l WHERE l.username = :username")
    List<Login> findByUsername(@Param("username") String username);
}

