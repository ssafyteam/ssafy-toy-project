package com.weareone.quicklog.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserSignUpDto {

    private String email;
    private String password;
    private String nickname;
    private LocalDate birth;
    private String name;

}
