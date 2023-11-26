package com.example.July.repository;

import com.example.July.entity.Team;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    List<Team> findByNameAndLocation(String name, String location, Pageable pageable);

    List<Team> findByName(String name, Pageable pageable);

    List<Team> findByLocation(String location, Pageable pageable);
}