package com.sp.sr.web.controller;

import com.sp.sr.admin.Auths;
import com.sp.sr.admin.SrAdminException;
import com.sp.sr.model.controller.BaseController;
import com.sp.sr.model.domain.Resource;
import com.sp.sr.model.enums.ResourceCategoryEnum;
import com.sp.sr.model.enums.ResultStatus;
import com.sp.sr.model.service.ResourceService;
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
@RequestMapping("/api/resource")
public class ResourceController extends BaseController {
    @Autowired
    private ResourceService resourceService;

    @RequestMapping("")
    @GetMapping
    public ResultVO<Page<Resource>> findAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                            @RequestParam(value = "size", defaultValue = "10") Integer size,
                                            @RequestParam Integer category) {
        if (category != null && category == 0) {
            return ResultVO.ok(resourceService.findAll(new PageRequest(page, size)));
        } else {
            return ResultVO.ok(resourceService.findAllByCategory(new PageRequest(page, size), category));
        }
    }

    @GetMapping(path = "/detail")
    public ResultVO<Resource> findOne(@RequestParam Long id) {
        Resource resource = resourceService.findById(id);
        if (resource == null) {
            throw new SrAdminException(ResultStatus.RESOURCE_NOT_FOUND);
        }
        Auths.verityUploader(USER.get(), resource.getUploaderId());
        return ResultVO.ok(resource);
    }
}
