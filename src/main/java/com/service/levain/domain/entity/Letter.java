package com.service.levain.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.service.levain.domain.dto.letter.request.AddLetterReqDto;
import com.service.levain.domain.enums.DeleteCheck;
import jakarta.persistence.*;
import lombok.*;
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

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_name")
    @JsonIgnore
    private Member member;

    // writer, content, iconNum만 포함하는 생성자
    @Builder
    public Letter(AddLetterReqDto reqDTO, Member member) {
        this.writer = reqDTO.getWriter();
        this.content = reqDTO.getContent();
        this.iconNum = reqDTO.getIconNum();
        this.member = member;
    }
    @PrePersist
    public void prePersist() {
        if (this.isDeleted == null) {
            this.isDeleted = DeleteCheck.N; // 기본값 설정
        }

        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now(); // createdAt 기본값 설정
        }
    }
    @Override
    public String toString() {
        return "Letter{" +
                "letterId=" + letterId +
                ", writer='" + writer + '\'' +
                ", content='" + content + '\'' +
                ", iconNum=" + iconNum +
                ", createdAt=" + createdAt +
                ", isDeleted=" + isDeleted +
                ", member=" + member +
                '}';
    }
}
