package com.ll.mutbooks.domain.post.model;

import com.ll.mutbooks.common.auditing.BaseEntity;
import com.ll.mutbooks.domain.member.model.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@SQLDelete(sql = "UPDATE post SET is_deleted = true WHERE id = ?")
@SQLRestriction(value = "is_deleted = false")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @Comment("작성자")
    private Member author;

    @Column(nullable = false)
    @Comment("제목")
    private String subject;

    @Column(nullable = false, columnDefinition = "text")
    @Comment("본문 내용(Markdown)")
    private String content;

    @Column(nullable = false, columnDefinition = "text")
    @Comment("본문 내용(HTML)")
    private String contentHtml;

    @Builder
    private Post(Member author, String subject, String content, String contentHtml) {
        this.author = author;
        this.subject = subject;
        this.content = content;
        this.contentHtml = contentHtml;
    }

    public static Post of(Member author, String subject, String content, String contentHtml) {
        return Post.builder()
                .author(author)
                .subject(subject)
                .content(content)
                .contentHtml(contentHtml)
                .build();
    }
}
