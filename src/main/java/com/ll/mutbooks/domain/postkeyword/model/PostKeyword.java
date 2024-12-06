package com.ll.mutbooks.domain.postkeyword.model;

import com.ll.mutbooks.common.auditing.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostKeyword extends BaseEntity {

    @Column(nullable = false)
    @Comment("해시 태그")
    private String content;

    @Builder
    private PostKeyword(String content) {
        this.content = content;
    }

    public static PostKeyword of(String content) {
        return PostKeyword.builder()
                .content(content)
                .build();
    }
}
