package com.sparta.springlv1.service;

import com.sparta.springlv1.dto.GeneralResponseDto;
import com.sparta.springlv1.dto.MemoRequestDto;
import com.sparta.springlv1.dto.MemoResponseDto;
import com.sparta.springlv1.dto.StatusResponseDto;
import com.sparta.springlv1.entity.Memo;
import com.sparta.springlv1.entity.User;
import com.sparta.springlv1.repository.MemoRepository;
import com.sparta.springlv1.repository.UserRepository;
import com.sparta.springlv1.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final JwtUtil jwtUtil;
    private final MemoRepository memoRepository;
    private final UserRepository userRepository;
    public MemoResponseDto createMemo(MemoRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validationToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
                User user = findUserByName(claims.getSubject());
                Memo memo = new Memo(requestDto);
                memo.setUser(user);
                memoRepository.save(memo);
                return new MemoResponseDto(memo);
            }
        }
        return null;
    }

    public List<MemoResponseDto> getAllMemo() {
        List<Memo> memoList = memoRepository.findAll();
        return memoList.stream().sorted((memo1, memo2) -> memo2.getModifiedAt().compareTo(memo1.getModifiedAt())).map(MemoResponseDto::new).collect(Collectors.toList());
    }

    public MemoResponseDto getMemo(Long id) {
        Memo memo = findMemoById(id);
        return new MemoResponseDto(memo);
    }

    public GeneralResponseDto updateMemo(Long id, MemoRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validationToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
                Memo memo = findMemoById(id);
                if (memo.getUser().getUsername().equals(claims.getSubject())) {
                    memo.update(requestDto);
                    memoRepository.save(memo); // (영속성 컨텍스트 개념으로는 자동으로 반영되는데 왜 나는 안되는지?)
                    return new MemoResponseDto(memo);
                }
            }
        }
        return new StatusResponseDto("직접 작성한 게시글만 수정할 수 있습니다.", HttpStatus.BAD_REQUEST);
    }

    public StatusResponseDto deleteMemo(Long id, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validationToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
                Memo memo = findMemoById(id);
                if (memo.getUser().getUsername().equals(claims.getSubject())) {
                    memoRepository.delete(memo);
                    return new StatusResponseDto("삭제 성공!!", HttpStatus.OK);
                }
            }
        }
        return new StatusResponseDto("직접 작성한 게시글만 삭제할 수 있습니다.", HttpStatus.BAD_REQUEST);
    }

    public Memo findMemoById(Long id) {
        return memoRepository.findById(id).orElseThrow(
                () -> new NullPointerException("입력하신 id의 게시글이 없습니다.")
        );
    }

    public User findUserByName(String username) {
        return userRepository.findById(username).orElseThrow(
                () -> new NullPointerException("등록되지 않은 사용자입니다.")
        );
    }

}
