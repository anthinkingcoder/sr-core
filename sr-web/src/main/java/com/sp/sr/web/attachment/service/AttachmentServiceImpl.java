package com.sp.sr.web.attachment.service;

import com.sp.sr.model.domain.attachment.Attachment;
import com.sp.sr.model.enums.ResultStatus;
import com.sp.sr.model.repository.attachment.AttachmentRepository;
import com.sp.sr.model.service.attachment.AttachmentService;
import com.sp.sr.web.SrWebException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttachmentServiceImpl implements AttachmentService {


    @Autowired
    private AttachmentRepository attachmentRepository;

    @Override
    public Attachment save(Attachment attachment) {
        throw new SrWebException(ResultStatus.SERVICE_ERROR);
    }

    @Override
    public Attachment findOne(Long id) {
        return attachmentRepository.findByIdAndDeleteAtIsNull(id);
    }

    @Override
    public List<Attachment> findAllByModuleCategoryAndModuleId(Integer moduleCategory, Long moduleId) {
        return attachmentRepository.findAllByModuleCategoryAndModuleIdAndDeleteAtIsNull(moduleCategory, moduleId);
    }
}
