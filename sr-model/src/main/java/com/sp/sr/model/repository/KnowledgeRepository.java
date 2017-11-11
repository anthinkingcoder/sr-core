package com.sp.sr.model.repository;

import com.sp.sr.model.domain.Knowledge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface KnowledgeRepository extends JpaRepository<Knowledge, Long> {
    Page<Knowledge> findByUploaderIdAndLevelAndDeleteAtIsNullOrderByCreateAtDesc(Long uploaderId, Pageable pageable, Integer level);

    Page<Knowledge> findAllByLevelAndDeleteAtIsNull(Pageable pageable, Integer level);


    Knowledge findByIdAndDeleteAtIsNull(Long id);

    Page<Knowledge> findAllByTopicIdAndLevelAndDeleteAtIsNullOrderByCreateAtDesc(Pageable pageable, Long topicId, Integer level);

    List<Knowledge> findAllByTopicIdAndDeleteAtIsNull(Long topicId);

    List<Knowledge> findAllByLevelAndDeleteAtIsNullOrderBySortAsc(Integer level);


    Page<Knowledge> findAllByLevelAndUploaderIdAndDeleteAtIsNull(Pageable pageable, Integer level, Long uploaderId);

    Page<Knowledge> findAllByTopicIdAndLevelAndUploaderIdAndDeleteAtIsNullOrderByCreateAtDesc(Pageable pageable, Long topicId, Integer level, Long uploaderId);

    List<Knowledge> findAllByTopicIdAndUploaderIdAndDeleteAtIsNull(Long topicId, Long uploaderId);

    List<Knowledge> findAllByLevelAndUploaderIdAndDeleteAtIsNull(Integer level, Long uploaderId);


}
