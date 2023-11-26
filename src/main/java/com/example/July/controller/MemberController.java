package com.example.July.controller;

import com.example.July.dto.MemberResponse;
import com.example.July.entity.Member;
import com.example.July.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        Member member = memberService.getMemberById(id);
        return ResponseEntity.ok(member);
    }

    @GetMapping
    public ResponseEntity<List<MemberResponse>> getAllMembers(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        List<MemberResponse> memberResponsePage = memberService.getAllMembers(firstName, lastName, PageRequest.of(page, size))
                .stream()
                .map(member -> {
                    MemberResponse memberResponse = new MemberResponse();
                    // Map Team fields to teamResponse fields
                    memberResponse.setId(member.getId());
                    memberResponse.setFirstName(member.getFirstName());
                    memberResponse.setLastName(member.getLastName());
                    memberResponse.setAddress(member.getAddress());
                    memberResponse.setJoinedDate(member.getJoinedDate());
                    return memberResponse;
                }).collect(Collectors.toList());

        return ResponseEntity.ok(memberResponsePage);
    }

    @PostMapping
    public ResponseEntity<Member> addMember(@RequestBody Member member) {
        Member addedMember = memberService.addMember(member);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedMember);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody Member member) {
        Member updatedMember = memberService.updateMember(id, member);
        return ResponseEntity.ok(updatedMember);
    }
}
