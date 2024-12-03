package com.ll.mutbooks.common.security;

import com.ll.mutbooks.domain.member.model.AuthLevel;
import com.ll.mutbooks.domain.member.model.Member;
import com.ll.mutbooks.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("회원 정보를 찾을 수 없습니다."));

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (member.getAuthLevel() == AuthLevel.NORMAL) {
            authorities.add(new SimpleGrantedAuthority(AuthLevel.NORMAL.name()));
        } else if(member.getAuthLevel() == AuthLevel.ADMIN) {
            authorities.add(new SimpleGrantedAuthority(AuthLevel.ADMIN.name()));
        } else {
            authorities.add(new SimpleGrantedAuthority(AuthLevel.AUTHOR.name()));
        }

        return new CustomUserDetails(member, authorities);
    }
}
