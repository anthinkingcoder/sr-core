package com.sp.sr.admin.reponsity;


import com.sp.sr.model.domain.Resource;
import com.sp.sr.model.domain.ResourceDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhoulin
 */
public interface ResourceRepository extends JpaRepository<Resource, Long> {
    Page<Resource> findAllByDeleteAtIsNull(Pageable pageable);

    Page<Resource> findAllByDeleteAtIsNullAndUploaderId(Pageable pageable,Long uploaderId);


    Resource findByIdAndDeleteAtIsNull(Long id);
}
