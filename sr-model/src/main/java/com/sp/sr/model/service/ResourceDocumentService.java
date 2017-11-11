package com.sp.sr.model.service;

import com.sp.sr.model.domain.ResourceDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author zhoulin
 */
public interface ResourceDocumentService {
    ResourceDocument save(ResourceDocument resourceDocument);

    ResourceDocument findOne(Long id);

    Page<ResourceDocument> findAll(Pageable pageable);
}
