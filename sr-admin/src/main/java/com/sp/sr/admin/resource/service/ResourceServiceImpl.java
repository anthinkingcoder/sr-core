package com.sp.sr.admin.resource.service;

import com.sp.sr.admin.common.Auths;
import com.sp.sr.model.repository.resource.ResourceRepository;
import com.sp.sr.model.service.ResourceService;
import com.sp.sr.model.domain.resource.Resource;
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
        if (Auths.isSystem(USER.get())) {
            return resourceRepository.findAllByDeleteAtIsNull(pageable);
        }
        return resourceRepository.findAllByDeleteAtIsNullAndUploaderId(pageable, USER.get().getId());
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
        return resourceRepository.findAllByDeleteAtIsNullAndCategory(pageable,category);
    }
}
