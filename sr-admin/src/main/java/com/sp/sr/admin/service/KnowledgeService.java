package com.sp.sr.admin.service;

import com.sp.sr.model.domain.Knowledge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface KnowledgeService {
    Page<Knowledge> findByUploaderIdAndLevelAndDeleteAtIsNull(Long uploaderId, Pageable pageable, Integer level);

    Page<Knowledge> findAllByDeleteAtIsNullAndLevel(Pageable pageable, Integer level);



    Knowledge save(Knowledge knowledge);

    Knowledge findById(Long id);

    Page<Knowledge> findAllByTopicIdAndLevel(Pageable pageable, Integer level, Long topicId);


    List<Knowledge> findAllByTopicId(Long topicId);

    List<Knowledge> findAllByLevel(Integer level);
}
