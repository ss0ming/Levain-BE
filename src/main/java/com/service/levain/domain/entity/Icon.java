package com.service.levain.domain.entity;

import com.service.levain.domain.dto.icons.request.PurchaseIconReqDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Icon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iconId;

    @Column
    private int iconNum;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_name")
    private Member member;

    @Builder
    public Icon(int iconNum, Member member) {
        this.iconNum = iconNum;
        this.member = member;
    }

    public static Icon purchaseOf(PurchaseIconReqDto dto, Member member) {
        return Icon.builder()
                .iconNum(dto.getIconNum())
                .member(member)
                .build();
    }
}
