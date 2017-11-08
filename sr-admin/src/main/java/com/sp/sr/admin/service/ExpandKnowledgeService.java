package com.sp.sr.admin.service;

import com.sp.sr.model.domain.ExpandKnowledge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExpandKnowledgeService {
    ExpandKnowledge findById(Long id);

    Page<ExpandKnowledge> findAll(Pageable pageable);

    ExpandKnowledge save(ExpandKnowledge expandKnowledge);
}
