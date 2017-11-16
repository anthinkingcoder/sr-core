package com.sp.sr.web.topic.service;

import com.sp.sr.model.domain.topic.Topic;
import com.sp.sr.model.enums.ResultStatus;
import com.sp.sr.model.repository.topic.TopicRepository;
import com.sp.sr.model.service.TopicService;
import com.sp.sr.web.SrWebException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhoulin
 */
@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicRepository repository;

    @Override
    public Page<Topic> findAll(Pageable pageable) {
        return repository.findAllByDeleteAtIsNull(pageable);
    }

    @Override
    public List<Topic> findAll() {
        return repository.findAllByDeleteAtIsNull();
    }

    @Override
    public Topic findById(Long id) {
        return repository.findByIdAndDeleteAtIsNull(id);
    }

    @Override
    public Topic save(Topic topic) {
        throw new SrWebException(ResultStatus.SERVICE_ERROR);
    }

    @Override
    public List<Topic> findAllByCategoryId(Long categoryId) {
        return repository.findAllByDeleteAtIsNullAndCategoryId(categoryId);
    }
}
