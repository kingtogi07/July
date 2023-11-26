package com.example.July.service;

import com.example.July.entity.Team;
import com.example.July.repository.TeamRepository;
import com.example.July.type.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public Team getTeamById(Long id) {
        return teamRepository.findById(id).orElseThrow(() -> new NotFoundException("Team not found"));
    }

    public List<Team> getAllTeams(String name, String location, Pageable pageable) {
        if (name != null && location != null) {
            return teamRepository.findByNameAndLocation(name, location, pageable);
        } else if (name != null) {
            return teamRepository.findByName(name, pageable);
        } else if (location != null) {
            return teamRepository.findByLocation(location, pageable);
        } else {
            return teamRepository.findAll(pageable).getContent();
        }
    }

    public Team addTeam(Team team) {
        return teamRepository.save(team);
    }

    public Team updateTeam(Long id, Team updatedTeam) {
        Team existingTeam = getTeamById(id);
        // Update fields as needed
        existingTeam.setName(updatedTeam.getName());
        existingTeam.setLocation(updatedTeam.getLocation());
        existingTeam.setFoundedDate(updatedTeam.getFoundedDate());
        existingTeam.setMembers(updatedTeam.getMembers());
        return teamRepository.save(existingTeam);
    }

    // Other methods as needed
}