package com.ll.mutbooks.domain.posthashtag.model;

import com.ll.mutbooks.common.auditing.BaseEntity;
import com.ll.mutbooks.domain.member.model.Member;
import com.ll.mutbooks.domain.post.model.Post;
import com.ll.mutbooks.domain.postkeyword.model.PostKeyword;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostHashtag extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @Comment("회원 ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @Comment("글 ID")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @Comment("해시태그 ID")
    private PostKeyword postKeyword;

    @Builder
    private PostHashtag(Member member, Post post, PostKeyword postKeyword) {
        this.member = member;
        this.post = post;
        this.postKeyword = postKeyword;
    }

    public static PostHashtag of(Member member, Post post, PostKeyword postKeyword) {
        return PostHashtag.builder()
                .member(member)
                .post(post)
                .postKeyword(postKeyword)
                .build();
    }
}
