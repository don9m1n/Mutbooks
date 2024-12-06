package com.ll.mutbooks.domain.post.service;

import com.ll.mutbooks.domain.member.model.Member;
import com.ll.mutbooks.domain.member.service.MemberService;
import com.ll.mutbooks.domain.post.dto.PostWriteForm;
import com.ll.mutbooks.domain.post.model.Post;
import com.ll.mutbooks.domain.post.repository.PostRepository;
import com.ll.mutbooks.domain.posthashtag.service.PostHashtagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final MemberService memberService;
    private final PostHashtagService postHashtagService;

    public void write(PostWriteForm form, long memberId) {

        // 1. 회원 정보 조회
        Member member = memberService.getMemberById(memberId);

        // 2. Post 객체 생성
        Post post = Post.of(member, form.getSubject(), form.getContent(), form.getContentHtml());
        postRepository.save(post);

        postHashtagService.savePostHashtag(post, form.getHashtags());
    }
}
