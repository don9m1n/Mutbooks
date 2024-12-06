package com.ll.mutbooks.domain.postkeyword.repository;

import com.ll.mutbooks.domain.postkeyword.model.PostKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostKeywordRepository extends JpaRepository<PostKeyword, Long> {

    Optional<PostKeyword> findByContent(String content);
}
