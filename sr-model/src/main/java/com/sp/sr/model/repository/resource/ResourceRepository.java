package com.sp.sr.model.repository.resource;


import com.sp.sr.model.domain.resource.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhoulin
 */
public interface ResourceRepository extends JpaRepository<Resource, Long> {
    Page<Resource> findAllByDeleteAtIsNull(Pageable pageable);

    Page<Resource> findAllByDeleteAtIsNullAndUploaderId(Pageable pageable, Long uploaderId);

    Page<Resource> findAllByDeleteAtIsNullAndCategory(Pageable pageable, Integer category);

    Resource findByIdAndDeleteAtIsNull(Long id);


}
