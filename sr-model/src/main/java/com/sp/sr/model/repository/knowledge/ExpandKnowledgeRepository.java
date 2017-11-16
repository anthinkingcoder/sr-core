package com.sp.sr.model.repository.knowledge;

import com.sp.sr.model.domain.knowledge.ExpandKnowledge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author zhoulin
 */
public interface ExpandKnowledgeRepository extends JpaRepository<ExpandKnowledge, Long> {
    ExpandKnowledge findByIdAndDeleteAtIsNull(Long id);

    Page<ExpandKnowledge> findAllByDeleteAtIsNullOrderByCreateAtAsc(Pageable pageable);

    Page<ExpandKnowledge> findAllByDeleteAtIsNullAndUploaderIdOrderByCreateAtAsc(Pageable pageable, Long UploaderId);

}
