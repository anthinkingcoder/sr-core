package com.sp.sr.admin.service.impl;

import com.sp.sr.admin.reponsity.KnowledgeRepository;
import com.sp.sr.admin.service.KnowledgeService;
import com.sp.sr.model.domain.Knowledge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhoulin
 */
@Service
public class KnowledgeServiceImpl implements KnowledgeService {
    @Autowired
    private KnowledgeRepository repository;

     @Override
    public Page<Knowledge> findByUploaderIdAndLevelAndDeleteAtIsNullOrderBySortDesc(Long uploaderId, Pageable pageable, Integer level) {
        return repository.findByUploaderIdAndLevelAndDeleteAtIsNullOrderBySortDesc(uploaderId, pageable, level);
    }

    @Override
    public Page<Knowledge> findAllByDeleteAtIsNullAndLevel(Pageable pageable, Integer level) {
        return repository.findAllByDeleteAtIsNullAndLevel(pageable, level);
    }



    @Override
    public Page<Knowledge> findAllByAndLevelAndDeleteAtIsNull(Pageable pageable, Integer level) {
        return repository.findAllByAndLevelAndDeleteAtIsNull(pageable, level);
    }

    @Override
    public Knowledge save(Knowledge knowledge) {
        return repository.save(knowledge);
    }
}
