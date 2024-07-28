package com.service.levain.domain.repository;

import com.service.levain.domain.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findByMemberUserName(String userName);

}
