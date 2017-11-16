package com.sp.sr.web.topic.service;

import com.sp.sr.model.domain.topic.TopicCategory;
import com.sp.sr.model.repository.topic.TopicCategoryRepository;
import com.sp.sr.model.service.TopicCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhoulin
 */
@Service
public class TopicCategoryServiceImpl implements TopicCategoryService{
    @Autowired
    private TopicCategoryRepository repository;

    @Override
    public List<TopicCategory> findAll() {
        return repository.findAllByDeleteAtIsNullOrderBySort();
    }

    @Override
    public TopicCategory save(TopicCategory topicCategory) {
        return repository.save(topicCategory);
    }

    @Override
    public TopicCategory findOne(Long id) {
        return repository.findByIdAndDeleteAtIsNull(id);
    }

}
