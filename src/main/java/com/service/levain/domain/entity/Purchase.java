package com.service.levain.domain.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long purchaseId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_name")
    private Member member;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private Icon icon;
}
