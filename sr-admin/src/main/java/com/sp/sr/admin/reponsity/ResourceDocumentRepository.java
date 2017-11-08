package com.sp.sr.admin.reponsity;

import com.sp.sr.model.domain.ResourceDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zhoulin
 */
public interface ResourceDocumentRepository extends JpaRepository<ResourceDocument, Long> {
    Page<ResourceDocument> findAllByDeleteAtIsNull(Pageable pageable);

    ResourceDocument findByIdAndDeleteAtIsNull(Long id);

    Page<ResourceDocument> findAllByDeleteAtIsNullAndUploaderId(Pageable pageable, Long uploaderId);

}
