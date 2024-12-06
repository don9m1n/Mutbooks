package com.ll.mutbooks.domain.member.service;

import com.ll.mutbooks.common.exception.member.DuplicateEmailException;
import com.ll.mutbooks.common.exception.member.DuplicateUsernameException;
import com.ll.mutbooks.domain.member.dto.JoinForm;
import com.ll.mutbooks.domain.member.model.Member;
import com.ll.mutbooks.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void join(JoinForm form) {

        memberRepository.findByUsername(form.getUsername()).ifPresent(member -> {
            throw new DuplicateUsernameException("이미 사용중인 아이디 입니다.");
        });

        memberRepository.findByEmail(form.getEmail()).ifPresent(member -> {
            throw new DuplicateEmailException("이미 사용중인 이메일 입니다.");
        });

        form.setPassword(passwordEncoder.encode(form.getPassword()));

        memberRepository.save(Member.of(form));
    }

    @Transactional(readOnly = true)
    public Member getMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 회원입니다."));
    }
}
