package com.ll.mutbooks.domain.post.service;

import com.ll.mutbooks.common.exception.post.NotFoundPostException;
import com.ll.mutbooks.domain.member.model.Member;
import com.ll.mutbooks.domain.member.service.MemberService;
import com.ll.mutbooks.domain.post.dto.PostDetailForm;
import com.ll.mutbooks.domain.post.dto.PostWriteForm;
import com.ll.mutbooks.domain.post.model.Post;
import com.ll.mutbooks.domain.post.repository.PostRepository;
import com.ll.mutbooks.domain.posthashtag.service.PostHashtagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final MemberService memberService;
    private final PostHashtagService postHashtagService;

    public Long write(PostWriteForm form, long memberId) {

        // 1. 회원 정보 조회
        Member member = memberService.getMemberById(memberId);

        // 2. Post 객체 생성
        Post post = Post.of(member, form.getSubject(), form.getContent(), form.getContentHtml());
        postRepository.save(post);

        postHashtagService.savePostHashtag(post, form.getHashtags());

        return post.getId();
    }

    @Transactional(readOnly = true)
    public PostDetailForm getPostDetail(long postId, long memberId) {
        Post post = postRepository.findByIdAndAuthor_Id(postId, memberId).orElseThrow(() -> new NotFoundPostException("해당 게시물이 존재하지 않습니다."));
        List<String> hashtagList = postHashtagService.getHashtagList(postId);

        return PostDetailForm.from(post, hashtagList);
    }
}
