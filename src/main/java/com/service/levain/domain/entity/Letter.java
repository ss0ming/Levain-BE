package com.service.levain.domain.entity;

import com.service.levain.domain.dto.letter.request.ReqDTO;
import com.service.levain.domain.enums.DeleteCheck;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Optional;

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

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_name")
    private Member member;

    // writer, content, iconNum만 포함하는 생성자
    @Builder
    public Letter(ReqDTO reqDTO, Member member) {
        this.writer = reqDTO.getWriter();
        this.content = reqDTO.getContent();
        this.iconNum = reqDTO.getIconNum();
        this.member = member;
    }

}
