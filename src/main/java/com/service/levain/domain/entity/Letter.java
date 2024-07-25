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
@AllArgsConstructor
public class Letter {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long letterId;

    @Column
    private String writer;

    @Column
    private String content;

    @Column
    @CreatedDate
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private DeleteCheck isDeleted;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_name")
    @JsonIgnore
    private Member member;

    @ManyToOne(optional = false)
    @JoinColumn(name = "icon_id")
    private Icon icon;

    public static Letter createLetter(String writer, String content, Member member, Icon icon) {
        return new Letter(null, writer, content, null, DeleteCheck.N, member, icon);
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
}
