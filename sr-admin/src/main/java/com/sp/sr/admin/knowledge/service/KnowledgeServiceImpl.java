package com.sp.sr.admin.knowledge.service;

import com.sp.sr.admin.common.Auths;
import com.sp.sr.model.repository.knowledge.KnowledgeRepository;
import com.sp.sr.model.service.KnowledgeService;
import com.sp.sr.model.controller.BaseController;
import com.sp.sr.model.domain.knowledge.Knowledge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.sp.sr.model.controller.BaseController.USER;


/**
 * @author zhoulin
 */
@Service
public class KnowledgeServiceImpl implements KnowledgeService {
    @Autowired
    private KnowledgeRepository repository;

    @Override
    public Page<Knowledge> findByUploaderIdAndLevelAndDeleteAtIsNull(Long uploaderId, Pageable pageable, Integer level) {
        return repository.findByUploaderIdAndLevelAndDeleteAtIsNullOrderByCreateAtDesc(uploaderId, pageable, level);
    }

    @Override
    public Page<Knowledge> findAllByDeleteAtIsNullAndLevel(Pageable pageable, Integer level) {
        if (Auths.isSystem(BaseController.USER.get())) {
            return repository.findAllByLevelAndDeleteAtIsNull(pageable, level);
        }
        return repository.findAllByLevelAndUploaderIdAndDeleteAtIsNull(pageable, level, USER.get().getId());
    }


    @Override
    public Knowledge save(Knowledge knowledge) {
        return repository.save(knowledge);
    }

    @Override
    public Knowledge findById(Long id) {
        return repository.findByIdAndDeleteAtIsNull(id);
    }

    @Override
    public Page<Knowledge> findAllByTopicIdAndLevel(Pageable pageable, Integer level, Long topicId) {
        if (Auths.isSystem(BaseController.USER.get())) {
            return repository.findAllByTopicIdAndLevelAndDeleteAtIsNullOrderByCreateAtDesc(pageable, topicId, level);
        }
        return repository.findAllByTopicIdAndLevelAndUploaderIdAndDeleteAtIsNullOrderByCreateAtDesc(pageable, topicId, level, BaseController.USER.get().getId());
    }

    @Override
    public List<Knowledge> findAllByTopicId(Long topicId) {
        if (Auths.isSystem(BaseController.USER.get())) {
            return repository.findAllByTopicIdAndDeleteAtIsNull(topicId);
        }

        return repository.findAllByTopicIdAndUploaderIdAndDeleteAtIsNull(topicId, BaseController.USER.get().getId());

    }

    @Override
    public List<Knowledge> findAllByLevel(Integer level) {
        if (Auths.isSystem(BaseController.USER.get())) {
            return repository.findAllByLevelAndDeleteAtIsNullOrderBySortAsc(level);
        }
        return repository.findAllByLevelAndUploaderIdAndDeleteAtIsNull(level, BaseController.USER.get().getId());
    }
}
