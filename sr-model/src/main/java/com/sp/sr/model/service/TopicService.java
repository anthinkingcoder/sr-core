package com.sp.sr.model.service;

import com.sp.sr.model.domain.topic.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author zhoulin
 */
public interface TopicService {

    Page<Topic> findAll(Pageable pageable);

    List<Topic> findAll();


    Topic findById(Long id);

    Topic save(Topic topic);

    List<Topic> findAllByCategoryId(Long categoryId);


}
