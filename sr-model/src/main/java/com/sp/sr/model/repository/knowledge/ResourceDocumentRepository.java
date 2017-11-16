package com.sp.sr.model.repository.knowledge;

import com.sp.sr.model.domain.knowledge.ResourceDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhoulin
 */
public interface ResourceDocumentRepository extends JpaRepository<ResourceDocument, Long> {
    Page<ResourceDocument> findAllByDeleteAtIsNull(Pageable pageable);

    ResourceDocument findByIdAndDeleteAtIsNull(Long id);

    Page<ResourceDocument> findAllByDeleteAtIsNullAndUploaderId(Pageable pageable, Long uploaderId);

}
