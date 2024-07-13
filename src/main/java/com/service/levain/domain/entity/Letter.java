package com.service.levain.domain.entity;

import com.service.levain.domain.enums.DeleteCheck;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.*;

@Entity
@Getter
@NoArgsConstructor
public class Letter {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long letterId;

    @Column
    private String writer;

    @Column
    private String content;

    @Column
    private int iconNum;

    @Column
    @CreatedDate
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private DeleteCheck isDeleted;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_name")
    private Member member;

}
