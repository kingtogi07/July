package com.example.July.repository;

import com.example.July.entity.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByFirstNameAndLastName(String firstName, String lastName, Pageable pageable);

    List<Member> findByFirstName(String firstName, Pageable pageable);

    List<Member> findByLastName(String lastName, Pageable pageable);
}