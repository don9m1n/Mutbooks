package com.ll.mutbooks.domain.post.dto;

import com.ll.mutbooks.domain.post.model.Post;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDetailForm {

    private Long postId;
    private String author;
    private String subject;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<String> hashtagList;

    public static PostDetailForm from(Post post, List<String> hashtagList) {
        return PostDetailForm.builder()
                .postId(post.getId())
                .author(post.getAuthor().getNickname())
                .subject(post.getSubject())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .modifiedAt(post.getModifiedAt())
                .hashtagList(hashtagList)
                .build();
    }
}
