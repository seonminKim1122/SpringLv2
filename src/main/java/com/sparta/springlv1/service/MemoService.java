package com.sparta.springlv1.service;

import com.sparta.springlv1.dto.MemoRequestDto;
import com.sparta.springlv1.dto.MemoResponseDto;
import com.sparta.springlv1.dto.PasswordRequestDto;
import com.sparta.springlv1.entity.Memo;
import com.sparta.springlv1.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;
    public MemoResponseDto createMemo(MemoRequestDto requestDto) {
        Memo memo = new Memo(requestDto);
        memoRepository.save(memo);
        return new MemoResponseDto(memo);
    }

    public List<MemoResponseDto> getAllMemo() {
        List<Memo> memoList = memoRepository.findAll();
        return memoList.stream().map(MemoResponseDto::new).sorted((memo1, memo2) -> memo2.getModifiedAt().compareTo(memo1.getModifiedAt())).collect(Collectors.toList());
    }

    public MemoResponseDto getMemo(Long id) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new NullPointerException("입력하신 id의 게시글이 없습니다.")
        );

        return new MemoResponseDto(memo);
    }

    public MemoResponseDto updateMemo(Long id, MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new NullPointerException("입력하신 id의 게시글이 없습니다.")
        );

        if(requestDto.getPassword().equals(memo.getPassword())) {
            memo.update(requestDto);
            memoRepository.save(memo);
            return new MemoResponseDto(memo);
        } else {
            return null;
        }
    }

    public String deleteMemo(Long id, PasswordRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new NullPointerException("입력하신 id의 게시글이 없습니다.")
        );

        if(requestDto.getPassword().equals(memo.getPassword())) {
            memoRepository.delete(memo);
            return "삭제 성공했습니다.";
        } else {
            return "비밀번호가 일치하지 않습니다.";
        }
    }
}
