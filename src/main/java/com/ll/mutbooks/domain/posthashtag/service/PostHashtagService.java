package com.ll.mutbooks.domain.posthashtag.service;

import com.ll.mutbooks.domain.post.model.Post;
import com.ll.mutbooks.domain.posthashtag.model.PostHashtag;
import com.ll.mutbooks.domain.posthashtag.repository.PostHashtagRepository;
import com.ll.mutbooks.domain.postkeyword.model.PostKeyword;
import com.ll.mutbooks.domain.postkeyword.service.PostKeywordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PostHashtagService {

    private final PostHashtagRepository postHashtagRepository;
    private final PostKeywordService postKeywordService;

    public void savePostHashtag(Post post, String hashtags) {
        // 문자열로 들어온 해시태그 분리
        List<String> hashtagList = Arrays.stream(hashtags.split("#"))
                .map(String::trim)
                .filter(hashtag -> !hashtag.isEmpty())
                .toList();

        hashtagList.forEach(hashtag -> {
            PostKeyword postKeyword = postKeywordService.savePostKeyword(hashtag);
            postHashtagRepository.save(PostHashtag.of(post.getAuthor(), post, postKeyword));
        });
    }

}
