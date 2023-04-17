package com.sparta.springlv1.controller;

import com.sparta.springlv1.dto.MemoRequestDto;
import com.sparta.springlv1.dto.MemoResponseDto;
import com.sparta.springlv1.dto.PasswordRequestDto;
import com.sparta.springlv1.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    // 특정 게시글 조회하기
    @GetMapping("/{id}")
    public MemoResponseDto getMemo(@PathVariable Long id) {
        return memoService.getMemo(id);
    }

    // 게시글 수정하기
    @PutMapping("/{id}")
    public MemoResponseDto updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        return memoService.updateMemo(id, requestDto);
    }

    // 게시글 삭제하기
    @DeleteMapping("/{id}")
    public String deleteMemo(@PathVariable Long id, @RequestBody PasswordRequestDto requestDto) {
        return memoService.deleteMemo(id, requestDto);
    }
}
