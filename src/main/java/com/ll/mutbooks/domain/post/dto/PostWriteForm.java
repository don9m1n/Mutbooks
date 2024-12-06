package com.ll.mutbooks.domain.post.dto;

import com.ll.mutbooks.domain.post.model.Post;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostWriteForm {

    @NotBlank(message = "제목은 필수 입력값 입니다.")
    private String subject;

    private String content;
    private String contentHtml;

    private String hashtags;

}
