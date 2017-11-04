package com.sp.sr.admin.reponsity;

import com.sp.sr.model.domain.Knowledge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KnowledgeRepository extends JpaRepository<Knowledge, Long> {
    Page<Knowledge> findByUploaderIdAndLevelAndDeleteAtIsNullOrderBySortDesc(Long uploaderId, Pageable pageable, Integer level);

    Page<Knowledge> findAllByDeleteAtIsNullAndLevel(Pageable pageable, Integer level);

    Page<Knowledge> findAllByAndLevelAndDeleteAtIsNull(Pageable pageable, Integer level);

}
