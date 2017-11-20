package com.sp.sr.web.knowledge.service;

import com.sp.sr.model.domain.knowledge.ResourceDocument;
import com.sp.sr.model.enums.ResultStatus;
import com.sp.sr.model.repository.knowledge.ResourceDocumentRepository;
import com.sp.sr.model.service.knowledge.ResourceDocumentService;
import com.sp.sr.web.SrWebException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author zhoulin
 */
@Service
public class ResourceDocumentServiceImpl implements ResourceDocumentService {

    @Autowired
    private ResourceDocumentRepository resourceDocumentRepository;

    @Override
    public ResourceDocument save(ResourceDocument resourceDocument) {
        throw new SrWebException(ResultStatus.SERVICE_ERROR);
    }

    @Override
    public ResourceDocument findOne(Long id) {
        return resourceDocumentRepository.findByIdAndDeleteAtIsNull(id);
    }

    @Override
    public Page<ResourceDocument> findAll(Pageable pageable) {
        throw new SrWebException(ResultStatus.SERVICE_ERROR);
    }
}
