package com.ll.mutbooks.domain.posthashtag.repository;

import com.ll.mutbooks.domain.posthashtag.model.PostHashtag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostHashtagRepository extends JpaRepository<PostHashtag, Long> {

    List<PostHashtag> findAllByPostId(Long postId);
}
