package com.sp.sr.admin.attachment.service;

import com.sp.sr.admin.common.Auths;
import com.sp.sr.model.repository.attachment.AttachmentRepository;
import com.sp.sr.model.service.attachment.AttachmentService;
import com.sp.sr.model.domain.attachment.Attachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.sp.sr.model.controller.BaseController.USER;

@Service
public class AttachmentServiceImpl implements AttachmentService{


    @Autowired
    private AttachmentRepository attachmentRepository;
    @Override
    public Attachment save(Attachment attachment) {
        return attachmentRepository.save(attachment);
    }

    @Override
    public Attachment findOne(Long id) {
        return attachmentRepository.findByIdAndDeleteAtIsNull(id);
    }

    @Override
    public List<Attachment> findAllByModuleCategoryAndModuleId(Integer moduleCategory, Long moduleId) {
        if (Auths.isSystem(USER.get())) {
            return attachmentRepository.findAllByModuleCategoryAndModuleIdAndDeleteAtIsNull(moduleCategory, moduleId);
        }
        return attachmentRepository.findAllByModuleCategoryAndModuleIdAndUploaderIdAndDeleteAtIsNull(moduleCategory, moduleId,USER.get().getId());

    }
}
