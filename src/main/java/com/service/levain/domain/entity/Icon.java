package com.service.levain.domain.entity;

import com.service.levain.domain.dto.icons.request.PurchaseIconReqDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Icon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iconId;

    @Column
    private String iconName;

    @Column
    private String icon_path;

    @Column
    private int price;

    public static Icon createIcon( String iconName, String icon_path, int price) {
        return new Icon(null,iconName, icon_path, price);
    }
}
