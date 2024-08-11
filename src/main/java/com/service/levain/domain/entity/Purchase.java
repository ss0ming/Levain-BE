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
    @JoinColumn(name = "icon_id")
    private Icon icon;

    public static Purchase createPurchase(Member member, Icon icon) {
        return new Purchase(null, member, icon);
    }
}
