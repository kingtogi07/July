package com.example.July.service;

import com.example.July.entity.Member;
import com.example.July.repository.MemberRepository;
import com.example.July.type.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public Member getMemberById(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new NotFoundException("Member not found"));
    }

    public List<Member> getAllMembers(String firstName, String lastName, Pageable pageable) {
        if (firstName != null && lastName != null) {
            return memberRepository.findByFirstNameAndLastName(firstName, lastName, pageable);
        } else if (firstName != null) {
            return memberRepository.findByFirstName(firstName, pageable);
        } else if (lastName != null) {
            return memberRepository.findByLastName(lastName, pageable);
        } else {
            return memberRepository.findAll(pageable).getContent();
        }
    }

    public Member addMember(Member member) {
        return memberRepository.save(member);
    }

    public Member updateMember(Long id, Member updatedMember) {
        Member existingMember = getMemberById(id);
        // Update fields as needed
        existingMember.setFirstName(updatedMember.getFirstName());
        existingMember.setLastName(updatedMember.getLastName());
        existingMember.setAddress(updatedMember.getAddress());
        existingMember.setJoinedDate(updatedMember.getJoinedDate());
        existingMember.setTeam(updatedMember.getTeam());
        return memberRepository.save(existingMember);
    }

    // Other methods as needed
}
