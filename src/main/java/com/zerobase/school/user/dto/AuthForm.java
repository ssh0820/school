package com.zerobase.school.user.dto;

import com.zerobase.school.user.domain.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthForm {
    private String email;

    private String name;

    private String password;

    private UserType userType;

    private String provider; //어떤 OAuth인지(google, naver 등)

    private String provideId; // 해당 OAuth 의 key(id)

    private LocalDateTime verifyExpiredAt;
}
