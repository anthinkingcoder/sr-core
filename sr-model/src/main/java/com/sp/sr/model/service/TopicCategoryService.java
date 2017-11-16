package com.sp.sr.model.service;

import com.sp.sr.model.domain.topic.TopicCategory;

import java.util.List;

public interface TopicCategoryService {
    List<TopicCategory> findAll();

    TopicCategory save(TopicCategory topicCategory);

    TopicCategory findOne(Long id);

}
