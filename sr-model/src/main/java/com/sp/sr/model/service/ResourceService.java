package com.sp.sr.model.service;

import com.sp.sr.model.domain.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ResourceService {
    Page<Resource> findAll(Pageable pageable);

    Resource findById(Long id);

    Resource save(Resource resource);

    Page<Resource> findAllByCategory(Pageable pageable, Integer category);

}
