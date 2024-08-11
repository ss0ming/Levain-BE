package com.service.levain.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
    private String iconPath;

    @Column
    private int price;

    public static Icon createIcon( String iconName, String iconPath, int price) {
        return new Icon(null,iconName, iconPath, price);
    }
}
