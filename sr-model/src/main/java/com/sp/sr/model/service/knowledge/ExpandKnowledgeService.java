package com.sp.sr.model.service.knowledge;

import com.sp.sr.model.domain.knowledge.ExpandKnowledge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExpandKnowledgeService {
    ExpandKnowledge findById(Long id);

    Page<ExpandKnowledge> findAll(Pageable pageable);

    ExpandKnowledge save(ExpandKnowledge expandKnowledge);

    Page<ExpandKnowledge> findAllByName(String title,Pageable pageable);
}
