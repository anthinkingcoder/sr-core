package com.sp.sr.admin.controller;

import com.sp.sr.admin.Auths;
import com.sp.sr.admin.SrAdminException;
import com.sp.sr.admin.service.ResourceService;
import com.sp.sr.model.domain.Resource;
import com.sp.sr.model.enums.ResourceCategoryEnum;
import com.sp.sr.model.enums.ResultStatus;
import com.sp.sr.model.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author zhoulin
 */
@RestController
@RequestMapping("/api/admin/resource")
public class ResourceController extends BaseController {
    @Autowired
    private ResourceService resourceService;


    @RequestMapping("/list")
    @GetMapping
    public ResultVO<Page<Resource>> findAll(@RequestParam(name = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return ResultVO.ok(resourceService.findAll(new PageRequest(page, size)));
    }

    @PostMapping(path = "/save")
    public ResultVO<Resource> save(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam String summary,
            @RequestParam(required = false) String coverUrl,
            @RequestParam String url,
            @RequestParam Integer category
    ) {
        if (!ResourceCategoryEnum.exists(category)) {
            throw new SrAdminException(ResultStatus.ARGUMENT_ERROR);
        }
        Resource resource;
        if (id != null && id != 0) {
            resource = resourceService.findById(id);
            if (resource == null) {
                throw new SrAdminException(ResultStatus.RESOURCE_NOT_FOUND);
            }
            Auths.verityUploader(USER.get(), resource.getUploaderId());
        } else {
            resource = new Resource();
            resource.setUploaderId(USER.get().getId());
        }
        resource.setCategory(category);
        resource.setCoverUrl(coverUrl);
        resource.setUrl(url);
        resource.setName(name);
        resource.setSummary(summary);
        resourceService.save(resource);
        return ResultVO.ok(resource);
    }

    @GetMapping(path = "/findOne")
    public ResultVO<Resource> findOne(@RequestParam Long id) {
        Resource resource = resourceService.findById(id);
        if (resource == null) {
            throw new SrAdminException(ResultStatus.RESOURCE_NOT_FOUND);
        }
        Auths.verityUploader(USER.get(), resource.getUploaderId());
        return ResultVO.ok(resource);
    }

    @PostMapping(path = "/delete")
    public ResultVO<Resource> delete(Long id) {
        Resource resource = resourceService.findById(id);
        if (resource == null) {
            throw new SrAdminException(ResultStatus.RESOURCE_NOT_FOUND);
        }
        Auths.verityUploader(USER.get(), resource.getUploaderId());
        resource.setDeleteAt(new Date());
        return ResultVO.ok(resource);
    }

}
