package com.sp.sr.admin.service;

import com.sp.sr.model.domain.Knowledge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface KnowledgeService {
    Page<Knowledge> findByUploaderIdAndLevelAndDeleteAtIsNullOrderBySortDesc(Long uploaderId, Pageable pageable, Integer level);

    Page<Knowledge> findAllByDeleteAtIsNullAndLevel(Pageable pageable, Integer level);


    Page<Knowledge> findAllByAndLevelAndDeleteAtIsNull(Pageable pageable, Integer level);

    Knowledge save(Knowledge knowledge);

}
