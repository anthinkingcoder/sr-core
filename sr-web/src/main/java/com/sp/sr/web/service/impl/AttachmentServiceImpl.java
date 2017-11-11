package com.sp.sr.web.service.impl;

import com.sp.sr.admin.Auths;
import com.sp.sr.model.domain.Attachment;
import com.sp.sr.model.enums.ResultStatus;
import com.sp.sr.model.repository.AttachmentRepository;
import com.sp.sr.model.service.AttachmentService;
import com.sp.sr.web.SrWebException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.sp.sr.model.controller.BaseController.USER;

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
