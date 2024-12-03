package com.ll.mutbooks.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinForm {

    @NotBlank(message = "아이디는 필수 입력 항목 입니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수 입력 항목 입니다.")
    private String password;

    @NotBlank(message = "이메일은 필수 입력 항목 입니다.")
    private String email;

}
