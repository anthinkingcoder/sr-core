package com.sp.sr.admin.topic.controller;

import com.sp.sr.admin.common.Auths;
import com.sp.sr.admin.common.SrAdminException;
import com.sp.sr.model.controller.BaseController;
import com.sp.sr.model.service.topic.TopicService;
import com.sp.sr.model.vo.ResultVO;
import com.sp.sr.model.domain.topic.Topic;
import com.sp.sr.model.enums.ResultStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author zhoulin
 */
@RestController
@RequestMapping("/api/admin/topic")
@Slf4j
public class TopicController extends BaseController {
    @Autowired
    private TopicService topicService;

    @RequestMapping("/list")
    @GetMapping
    public ResultVO<Page<Topic>> findAll(@RequestParam(name = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return ResultVO.ok(topicService.findAll(new PageRequest(page, size)));
    }

    @RequestMapping("/all")
    @GetMapping
    public ResultVO<List<Topic>> findAll() {
        return ResultVO.ok(topicService.findAll());
    }

    @PostMapping(path = "/save")
    public ResultVO<Topic> save(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam(required = false) String coverUrl,
            @RequestParam Long categoryId) {

        Topic topic;
        if (id != null && id != 0) {

            topic = topicService.findById(id);
            if (topic == null) {
                throw new SrAdminException(ResultStatus.RESOURCE_NOT_FOUND);
            }
            Auths.verityUploader(USER.get(), topic.getUploaderId());
            topic.setUpdateAt(new Date());
        } else {
            topic = new Topic();
            topic.setUploaderId(USER.get().getId());
        }
        topic.setName(name);
        topic.setCoverUrl(coverUrl);
        topic.setCategoryId(categoryId);
        topic = topicService.save(topic);
        return ResultVO.ok(topic);

    }

    @GetMapping(path = "/findOne")
    public ResultVO<Topic> findOne(@RequestParam Long id) {
        Topic topic = topicService.findById(id);
        if (topic == null) {
            throw new SrAdminException(ResultStatus.RESOURCE_NOT_FOUND);
        }
        Auths.verityUploader(USER.get(), topic.getUploaderId());
        return ResultVO.ok(topic);
    }

    @PostMapping("/delete")
    public ResultVO<Object> delete(@RequestParam Long id) {
        Topic topic = topicService.findById(id);
        if (topic == null) {
            throw new SrAdminException(ResultStatus.RESOURCE_NOT_FOUND);
        }
        topic.setDeleteAt(new Date());
        topicService.save(topic);
        Auths.verityUploader(USER.get(), topic.getUploaderId());
        return ResultVO.ok(topic);
    }
}
