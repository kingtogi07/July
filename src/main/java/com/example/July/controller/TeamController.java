package com.example.July.controller;

import com.example.July.dto.MemberResponse;
import com.example.July.dto.TeamResponse;
import com.example.July.entity.Team;
import com.example.July.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable Long id) {
        Team team = teamService.getTeamById(id);
        return ResponseEntity.ok(team);
    }

    @GetMapping
    public ResponseEntity<List<TeamResponse>> getAllTeams(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String location,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        List<TeamResponse> teamResponsePage = teamService.getAllTeams(name, location, PageRequest.of(page, size))
                .stream()
                .map(team -> {
                    TeamResponse teamResponse = new TeamResponse();
                    // Map Team fields to teamResponse fields
                    teamResponse.setId(team.getId());
                    teamResponse.setName(team.getName());
                    teamResponse.setFoundedDate(team.getFoundedDate());
                    teamResponse.setLocation(team.getLocation());
                    // Map members using memberResponse
                    teamResponse.setMembers(team.getMembers().stream()
                            .map(member -> {
                                MemberResponse memberResponse = new MemberResponse();
                                memberResponse.setId(member.getId());
                                memberResponse.setFirstName(member.getFirstName());
                                memberResponse.setLastName(member.getLastName());
                                memberResponse.setAddress(member.getAddress());
                                memberResponse.setJoinedDate(member.getJoinedDate());
                                return memberResponse;
                            })
                            .collect(Collectors.toList()));
                    return teamResponse;
                }).collect(Collectors.toList());

        return ResponseEntity.ok(teamResponsePage);
    }

    @PostMapping
    public ResponseEntity<Team> addTeam(@RequestBody Team team) {
        Team addedTeam = teamService.addTeam(team);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedTeam);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable Long id, @RequestBody Team team) {
        Team updatedTeam = teamService.updateTeam(id, team);
        return ResponseEntity.ok(updatedTeam);
    }
}

