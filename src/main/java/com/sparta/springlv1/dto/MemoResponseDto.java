package com.sparta.springlv1.dto;

import com.sparta.springlv1.entity.Memo;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MemoResponseDto implements GeneralResponseDto {
    private String title;
    private String name;
    private String content;
    private LocalDate modifiedAt;

    public MemoResponseDto(Memo memo) {
        this.title = memo.getTitle();
        this.name = memo.getUser().getUsername();
        this.content = memo.getContent();
        this.modifiedAt = memo.getModifiedAt().toLocalDate();
    }
}
