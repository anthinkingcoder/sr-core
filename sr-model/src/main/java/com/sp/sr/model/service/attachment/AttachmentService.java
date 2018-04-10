package com.sp.sr.model.service.attachment;

import com.sp.sr.model.domain.attachment.Attachment;

import java.util.List;

public interface AttachmentService {
    Attachment save(Attachment attachment);

    Attachment findOne(Long id);

    List<Attachment> findAllByModuleCategoryAndModuleId(Integer moduleCategory, Long moduleId);
}
