package com.sp.sr.web.service.impl;

import com.sp.sr.admin.Auths;
import com.sp.sr.model.domain.Resource;
import com.sp.sr.model.repository.ResourceRepository;
import com.sp.sr.model.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.sp.sr.model.controller.BaseController.USER;

/**
 * @author zhoulin
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    @Override
    public Page<Resource> findAll(Pageable pageable) {
        return resourceRepository.findAllByDeleteAtIsNull(pageable);
    }

    @Override
    public Resource findById(Long id) {
        return resourceRepository.findByIdAndDeleteAtIsNull(id);
    }

    @Override
    public Resource save(Resource resource) {
        return resourceRepository.save(resource);
    }

    @Override
    public Page<Resource> findAllByCategory(Pageable pageable, Integer category) {
        return resourceRepository.findAllByDeleteAtIsNullAndCategory(pageable, category);
    }
}
