package com.sp.sr.admin.service;

import com.sp.sr.model.domain.Attachment;
import com.sp.sr.model.domain.ResourceDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AttachmentService {
    Attachment save(Attachment attachment);

    Attachment findOne(Long id);

    List<Attachment> findAllByModuleCategoryAndModuleId(Integer moduleCategory, Long moduleId);
}
