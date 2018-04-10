package com.sp.sr.model.repository.example;

import com.sp.sr.model.domain.example.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zhoulin
 */
public interface ExampleRepository extends JpaRepository<Example, Long> {
    Example findByIdAndDeleteAtIsNull(Long id);


    Page<Example> findAllByDeleteAtIsNullOrderByCreateAtAsc(Pageable pageable);

    Page<Example> findAllByDeleteAtIsNullAndUploaderIdOrderByCreateAtAsc(Pageable pageable, Long uploaderId);


    List<Example> findAllByKnowledgeIdAndDeleteAtIsNullOrderByLevel(Long knowledgeId);

    Page<Example> findAllByDeleteAtIsNullAndKnowledgeIdOrderByCreateAtAsc(Pageable pageable, Long knowledgeId);

    Page<Example> findAllByDeleteAtIsNullAndUploaderIdAndKnowledgeIdOrderByCreateAtAsc(Pageable pageable, Long uploaderId, Long knowledgeId);

}
