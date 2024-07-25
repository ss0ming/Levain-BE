package com.service.levain.domain.repository;

import com.service.levain.domain.entity.Icon;
import com.service.levain.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IconRepository extends JpaRepository<Icon, Long> {

//    List<Icon> findIconNumByMember(Member member);
}
