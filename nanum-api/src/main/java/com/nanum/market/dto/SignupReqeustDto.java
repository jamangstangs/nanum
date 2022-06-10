package com.nanum.market.dto;


import com.nanum.market.model.User;
import lombok.*;

import javax.validation.constraints.*;


@RequiredArgsConstructor
@Getter
@Setter
public class SignupReqeustDto {


    @NotBlank(message = "이메일을 비워둘 수 없습니다.")
    @Email(message = "메일 양식을 지켜주세요.")
    private String username;

    @NotBlank(message = "비밀번호를 비워둘 수 없습니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,20}$",
            message = "비밀번호는 영문 대소문자와 숫자,특수문자를 포함한 8-20자여야합니다.")
    private String password;

    @NotBlank(message = "닉네임을 비워둘 수 없습니다.")
    private String nickname;

    @NotBlank(message = "인증코드를 비워둘 수 없습니다.")
    private String verificationCode;



    @Builder
    public SignupReqeustDto(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;

    }

    public User toEntity(){
        return User.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .build();
    }
}
