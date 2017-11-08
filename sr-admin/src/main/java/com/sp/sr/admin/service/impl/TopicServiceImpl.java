package com.sp.sr.admin.service.impl;

import com.sp.sr.admin.Auths;
import com.sp.sr.admin.reponsity.TopicRepository;
import com.sp.sr.admin.service.TopicService;
import com.sp.sr.model.domain.Topic;
import com.sp.sr.model.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.sp.sr.admin.controller.BaseController.USER;

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
}
