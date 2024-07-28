package com.service.levain.domain.repository;

import com.service.levain.domain.entity.Member;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, String>{
    @Modifying
    @Transactional
    @Query("update Member m set m.reward = m.reward + 1 where m.userName = :userName")
    void incrementReward(@Param("userName") String userName);

    boolean existsByUserName(String userName);
}
