package com.ll.mutbooks.common.data;

import com.ll.mutbooks.domain.member.model.AuthLevel;
import com.ll.mutbooks.domain.member.model.Member;
import com.ll.mutbooks.domain.member.repository.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class LocalInitMemberData {
    @Bean
    public CommandLineRunner initMemberData(MemberRepository memberRepository, PasswordEncoder passwordEncoder){
        return args -> {
            Member member1 = Member.of("user", passwordEncoder.encode("1234"), "user@email.com", null, AuthLevel.NORMAL);
            Member member2 = Member.of("admin", passwordEncoder.encode("1234"), "admin@email.com", null, AuthLevel.ADMIN);

            memberRepository.save(member1);
            memberRepository.save(member2);
        };
    }
}
