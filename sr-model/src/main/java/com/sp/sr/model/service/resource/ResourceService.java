package com.sp.sr.model.service.resource;

import com.sp.sr.model.domain.knowledge.Knowledge;
import com.sp.sr.model.domain.resource.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ResourceService {
    Page<Resource> findAll(Pageable pageable);

    Resource findById(Long id);

    Resource save(Resource resource);

    Page<Resource> findAllByCategory(Pageable pageable, Integer category);

    Page<Resource> findAllByName(String name,Pageable pageable);

}
