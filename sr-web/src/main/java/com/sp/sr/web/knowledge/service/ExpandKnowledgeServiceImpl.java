package com.sp.sr.web.knowledge.service;

import com.sp.sr.model.domain.knowledge.ExpandKnowledge;
import com.sp.sr.model.enums.ResultStatus;
import com.sp.sr.model.repository.knowledge.ExpandKnowledgeRepository;
import com.sp.sr.model.service.knowledge.ExpandKnowledgeService;
import com.sp.sr.web.SrWebException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
            return repository.findAllByDeleteAtIsNullOrderByCreateAtAsc(pageable);
    }

    @Override
    public ExpandKnowledge save(ExpandKnowledge expandKnowledge) {
        throw new SrWebException(ResultStatus.SERVICE_ERROR);
    }

    @Override
    public Page<ExpandKnowledge> findAllByName(String name, Pageable pageable) {
        return repository.findAllByDeleteAtIsNullAndNameContains(name,pageable);
    }


}
