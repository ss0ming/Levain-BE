package com.service.levain.domain.dto.letter.response;

import com.service.levain.domain.entity.Letter;
import com.service.levain.domain.enums.DeleteCheck;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class LetterResDto {
    private Long letterId;
    private String content;
    private String writer;
    private String receiver;
    private DeleteCheck isDeleted;
    private LocalDateTime createdAt;
    private Long iconId;
    private String iconPath;
    private String iconName;

    public static LetterResDto from(Letter letter) {
        LetterResDto letterResDto = new LetterResDto();
        letterResDto.letterId = letter.getLetterId();
        letterResDto.content = letter.getContent();
        letterResDto.writer = letter.getWriter();
        letterResDto.receiver = letter.getMember().getUserName();
        letterResDto.iconId = letter.getIcon().getIconId();
        letterResDto.isDeleted = letter.getIsDeleted();
        letterResDto.createdAt = letter.getCreatedAt();
        letterResDto.iconPath = letter.getIcon().getIconPath();
        letterResDto.iconName = letter.getIcon().getIconName();

        return letterResDto;
    }
}
