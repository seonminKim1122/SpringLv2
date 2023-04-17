package com.sparta.springlv1.repository;

import com.sparta.springlv1.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<Memo, Long> {
}
