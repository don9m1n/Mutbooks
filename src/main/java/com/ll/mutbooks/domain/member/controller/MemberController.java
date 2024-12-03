package com.ll.mutbooks.domain.member.controller;

import com.ll.mutbooks.common.exception.member.DuplicateEmailException;
import com.ll.mutbooks.common.exception.member.DuplicateUsernameException;
import com.ll.mutbooks.domain.member.dto.JoinForm;
import com.ll.mutbooks.domain.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    @PreAuthorize("isAnonymous()")
    public String login() {
        return "member/login";
    }

    @GetMapping("/join")
    @PreAuthorize("isAnonymous()")
    public String joinForm(JoinForm joinForm) {
        return "member/join";
    }

    @PostMapping("/join")
    @PreAuthorize("isAnonymous()")
    public String join(@Valid JoinForm joinForm, BindingResult bindingResult) {

        // 형식에 맞지 않는 입력이 들어온 경우
        if(bindingResult.hasErrors()) {
            return "member/join";
        }

        try {
            memberService.join(joinForm);
        } catch (DuplicateUsernameException e) {
            bindingResult.rejectValue("username", "duplicate_username", e.getMessage());
            return "member/join";
        } catch (DuplicateEmailException e) {
            bindingResult.rejectValue("email", "duplicate_email", e.getMessage());
            return "member/join";
        }

        return "redirect:/";
    }


}
