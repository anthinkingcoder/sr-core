package com.sp.sr.web.knowledge.service;

import com.sp.sr.model.domain.knowledge.Knowledge;
import com.sp.sr.model.enums.ResultStatus;
import com.sp.sr.model.repository.knowledge.KnowledgeRepository;
import com.sp.sr.model.service.KnowledgeService;
import com.sp.sr.web.SrWebException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KnowledgeServiceImpl implements KnowledgeService {
    @Autowired
    private KnowledgeRepository knowledgeRepository;

    @Override
    public Page<Knowledge> findByUploaderIdAndLevelAndDeleteAtIsNull(Long uploaderId, Pageable pageable, Integer level) {
        throw new SrWebException(ResultStatus.SERVICE_ERROR);
    }

    @Override
    public Page<Knowledge> findAllByDeleteAtIsNullAndLevel(Pageable pageable, Integer level) {
        return null;
    }

    @Override
    public Knowledge save(Knowledge knowledge) {
        throw new SrWebException(ResultStatus.SERVICE_ERROR);
    }

    @Override
    public Knowledge findById(Long id) {
        return knowledgeRepository.findByIdAndDeleteAtIsNull(id);
    }

    @Override
    public Page<Knowledge> findAllByTopicIdAndLevel(Pageable pageable, Integer level, Long topicId) {
        return knowledgeRepository.findAllByTopicIdAndLevelAndDeleteAtIsNullOrderByCreateAtDesc(pageable,topicId,level);
    }

    @Override
    public List<Knowledge> findAllByTopicId(Long topicId) {
        return knowledgeRepository.findAllByTopicIdAndDeleteAtIsNull(topicId);
    }

    @Override
    public List<Knowledge> findAllByLevel(Integer level) {
        return knowledgeRepository.findAllByLevelAndDeleteAtIsNullOrderBySortAsc(level);
    }
}
