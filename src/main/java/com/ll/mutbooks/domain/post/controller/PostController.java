package com.ll.mutbooks.domain.post.controller;

import com.ll.mutbooks.common.security.CustomUserDetails;
import com.ll.mutbooks.domain.post.dto.PostWriteForm;
import com.ll.mutbooks.domain.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    @PostMapping("/{postId}/delete")
    public String delete(@PathVariable("postId") Long postId, @AuthenticationPrincipal CustomUserDetails user) {
        postService.delete(postId, user.getMember().getId());
        return "redirect:/posts"; // TODO: 글 목록 조회 구현
    }

    @GetMapping("/{postId}")
    public String detail(
            @PathVariable("postId") Long postId,
            @AuthenticationPrincipal CustomUserDetails user,
            Model model
    ) {

        // TODO: 글 조회 권한 추가
        model.addAttribute("postDetailForm", postService.getPostDetail(postId, user.getMember().getId()));
        return "post/detail";
    }

    @GetMapping("/write")
    @PreAuthorize("isAuthenticated()")
    public String writeForm(PostWriteForm form) {
        return "post/write";
    }

    @PostMapping("/write")
    @PreAuthorize("isAuthenticated()")
    public String write(
            @AuthenticationPrincipal CustomUserDetails user,
            @Valid PostWriteForm form,
            BindingResult bindingResult
    ) {

        if (bindingResult.hasErrors()) {
            return "post/write";
        }

        long postId;

        try {
            postId = postService.write(form, user.getMember().getId());
        } catch (UsernameNotFoundException e) {
            log.error("인증 관련 에러 발생: {}", e.getMessage());
            return "member/login";
        } catch (Exception e) {
            log.error("글 작성 중 에러 발생: {}", e.getMessage());
            return "post/write";
        }

        return "redirect:/posts/%d".formatted(postId);
    }
}
