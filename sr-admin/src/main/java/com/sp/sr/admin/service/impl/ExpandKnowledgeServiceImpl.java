package com.sp.sr.admin.service.impl;

import com.sp.sr.admin.Auths;
import com.sp.sr.admin.controller.BaseController;
import com.sp.sr.admin.reponsity.ExpandKnowledgeRepository;
import com.sp.sr.admin.service.ExpandKnowledgeService;
import com.sp.sr.model.domain.ExpandKnowledge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.sp.sr.admin.controller.BaseController.USER;

/**
 * @author zhoulin
 */
@Service
public class ExpandKnowledgeServiceImpl implements ExpandKnowledgeService {

    @Autowired
    private ExpandKnowledgeRepository repository;

    @Override
    public ExpandKnowledge findById(Long id) {
        return repository.findByIdAndDeleteAtIsNull(id);
    }

    @Override
    public Page<ExpandKnowledge> findAll(Pageable pageable) {
        if (Auths.isSystem(USER.get())) {
            return repository.findAllByDeleteAtIsNullOrderByCreateAtAsc(pageable);

        } else {
            return repository.findAllByDeleteAtIsNullAndUploaderIdOrderByCreateAtAsc(pageable, USER.get().getId());
        }
    }

    @Override
    public ExpandKnowledge save(ExpandKnowledge expandKnowledge) {
        return repository.save(expandKnowledge);
    }
}
