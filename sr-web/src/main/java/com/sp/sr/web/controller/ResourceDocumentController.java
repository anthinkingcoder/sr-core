package com.sp.sr.web.controller;

import com.sp.sr.admin.SrAdminException;
import com.sp.sr.model.controller.BaseController;
import com.sp.sr.model.domain.ResourceDocument;
import com.sp.sr.model.enums.ResultStatus;
import com.sp.sr.model.service.ResourceDocumentService;
import com.sp.sr.model.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhoulin
 */
@RestController
@RequestMapping("/api/resource_document")
@Slf4j
public class ResourceDocumentController extends BaseController {
    @Autowired
    private ResourceDocumentService resourceDocumentService;



    @GetMapping(path = "")
    public ResultVO<ResourceDocument> findOne(@RequestParam Long id) {
        ResourceDocument resourceDocument = resourceDocumentService.findOne(id);
        if (resourceDocument == null) {
            throw new SrAdminException(ResultStatus.RESOURCE_NOT_FOUND);
        }
        return ResultVO.ok(resourceDocument);
    }


}
