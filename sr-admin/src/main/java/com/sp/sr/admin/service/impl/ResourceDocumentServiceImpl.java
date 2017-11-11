package com.sp.sr.admin.service.impl;

import com.sp.sr.admin.Auths;
import com.sp.sr.model.domain.ResourceDocument;
import com.sp.sr.model.repository.ResourceDocumentRepository;
import com.sp.sr.model.service.ResourceDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.sp.sr.model.controller.BaseController.USER;

/**
 * @author zhoulin
 */
@Service
public class ResourceDocumentServiceImpl implements ResourceDocumentService {

    @Autowired
    private ResourceDocumentRepository resourceDocumentRepository;

    @Override
    public ResourceDocument save(ResourceDocument resourceDocument) {
        return resourceDocumentRepository.save(resourceDocument);
    }

    @Override
    public ResourceDocument findOne(Long id) {
        return resourceDocumentRepository.findByIdAndDeleteAtIsNull(id);
    }

    @Override
    public Page<ResourceDocument> findAll(Pageable pageable) {
        if (Auths.isSystem(USER.get())) {
            return resourceDocumentRepository.findAllByDeleteAtIsNull(pageable);
        }
        return resourceDocumentRepository.findAllByDeleteAtIsNullAndUploaderId(pageable, USER.get().getId());
    }
}
