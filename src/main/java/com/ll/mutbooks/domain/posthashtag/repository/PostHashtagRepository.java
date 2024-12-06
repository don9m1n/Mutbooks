package com.ll.mutbooks.domain.posthashtag.repository;

import com.ll.mutbooks.domain.posthashtag.model.PostHashtag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostHashtagRepository extends JpaRepository<PostHashtag, Long> {
}
