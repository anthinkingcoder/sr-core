package com.sp.sr.model.service;

import com.sp.sr.model.domain.Attachment;

import java.util.List;

public interface AttachmentService {
    Attachment save(Attachment attachment);

    Attachment findOne(Long id);

    List<Attachment> findAllByModuleCategoryAndModuleId(Integer moduleCategory, Long moduleId);
}
