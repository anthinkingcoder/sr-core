package com.sp.sr.admin.topic.service;

import com.sp.sr.admin.common.Auths;
import com.sp.sr.model.repository.topic.TopicRepository;
import com.sp.sr.model.service.TopicService;
import com.sp.sr.model.domain.topic.Topic;
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
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicRepository repository;

    @Override
    public Page<Topic> findAll(Pageable pageable) {
        if (Auths.isSystem(USER.get())) {
            return repository.findAllByDeleteAtIsNull(pageable);
        }
        return repository.findAllByDeleteAtIsNullAndUploaderId(pageable, USER.get().getId());
    }

    @Override
    public List<Topic> findAll() {
        if (Auths.isSystem(USER.get())) {
            return repository.findAllByDeleteAtIsNull();
        }
        return repository.findAllByDeleteAtIsNullAndUploaderId(USER.get().getId());
    }

    @Override
    public Topic findById(Long id) {
        return repository.findByIdAndDeleteAtIsNull(id);
    }

    @Override
    public Topic save(Topic topic) {
        return repository.save(topic);
    }

    @Override
    public List<Topic> findAllByCategoryId(Long categoryId) {
        return repository.findAllByDeleteAtIsNullAndCategoryId(categoryId);
    }
}
