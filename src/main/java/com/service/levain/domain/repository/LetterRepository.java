package com.service.levain.domain.repository;

import com.service.levain.domain.entity.Letter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LetterRepository extends JpaRepository<Letter, Long> {
    Page<Letter> findByMemberUserName(String userName, Pageable pageable);
}
