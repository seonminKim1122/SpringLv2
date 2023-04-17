package com.sparta.springlv1.controller;

import com.sparta.springlv1.dto.MemoRequestDto;
import com.sparta.springlv1.dto.MemoResponseDto;
import com.sparta.springlv1.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;
    // 게시글 작성하기
    @PostMapping("/create")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto) {
        return memoService.createMemo(requestDto);
    }

    // 전체 게시글 조회하기
    @GetMapping("/list")
    public List<MemoResponseDto> getAllMemo() {
        return memoService.getAllMemo();
    }
}
