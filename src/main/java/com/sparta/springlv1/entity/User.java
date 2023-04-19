package com.sparta.springlv1.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

}
