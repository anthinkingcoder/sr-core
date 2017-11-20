package com.sp.sr.model.repository.topic;

import com.sp.sr.model.domain.resource.Resource;
import com.sp.sr.model.domain.topic.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zhoulin
 */
public interface TopicRepository extends JpaRepository<Topic, Long> {
    Page<Topic> findAllByDeleteAtIsNull(Pageable pageable);

    Page<Topic> findAllByDeleteAtIsNullAndUploaderId(Pageable pageable, Long uploaderId);

    Page<Topic> findAllByDeleteAtIsNullAndNameContains(String name, Pageable pageable);

    Topic findByIdAndDeleteAtIsNull(Long id);

    List<Topic> findAllByDeleteAtIsNull();

    List<Topic> findAllByDeleteAtIsNullAndUploaderId(Long uploaderId);


    List<Topic> findAllByDeleteAtIsNullAndCategoryId(Long categoryId);


}
