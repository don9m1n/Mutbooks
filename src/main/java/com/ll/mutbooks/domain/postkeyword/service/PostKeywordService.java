package com.ll.mutbooks.domain.postkeyword.service;

import com.ll.mutbooks.domain.postkeyword.model.PostKeyword;
import com.ll.mutbooks.domain.postkeyword.repository.PostKeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostKeywordService {

    private final PostKeywordRepository postKeywordRepository;

    public PostKeyword savePostKeyword(String content) {
        // 해당 태그가 있으면 기존 태그를 반환하고, 없으면 저장 후 반환
        return postKeywordRepository.findByContent(content)
                .orElseGet(() -> postKeywordRepository.save(PostKeyword.of(content)));
    }
}
