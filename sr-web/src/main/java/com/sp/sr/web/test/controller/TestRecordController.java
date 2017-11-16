package com.sp.sr.web.test.controller;

import com.sp.sr.model.domain.test.TestRecord;
import com.sp.sr.model.dto.TestRecordDTO;
import com.sp.sr.model.service.TestRecordService;
import com.sp.sr.model.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhoulin
 */
@RestController
@RequestMapping("/api/test/record")
@Slf4j
public class TestRecordController {

    @Autowired
    private TestRecordService testRecordService;

    /**
     * 生成一次测试
     *
     * @param categoryId  题目列表id数组字符串 以,分隔
     * @param level       测试难度
     * @param questionNum 测试题目数
     * @param origin      题目来源
     * @return return json data
     */
    @PostMapping("/create")
    public ResultVO<Object> create(@RequestParam String categoryId,
                                   @RequestParam Integer level,
                                   @RequestParam Integer questionNum,
                                   @RequestParam Integer origin
    ) {
        String[] categoryIds = categoryId.split(",");
        TestRecordDTO testRecordDTO = new TestRecordDTO(categoryIds, level, questionNum, origin);
        testRecordDTO = testRecordService.create(testRecordDTO);

        return ResultVO.ok(testRecordDTO);
    }

    @GetMapping("/detail")
    public ResultVO<TestRecordDTO> detail(@RequestParam Long testRecordId) {
        TestRecordDTO testRecordDTO = testRecordService.findOne(testRecordId);
        testRecordDTO.setQuestionTestRecord(testRecordService.findOneQuestionTestRecord(testRecordDTO.getQuestionIds().get(0), testRecordId));
        return ResultVO.ok(testRecordDTO);
    }

    @GetMapping("/next")
    public ResultVO<TestRecordDTO> next(@RequestParam Long testRecordId,
                                        @RequestParam Long nextQuestionId,
                                        @RequestParam(required = false) Long questionId,
                                        @RequestParam(required = false) String answer) {
        if (StringUtils.hasText(answer)) {
            testRecordService.updateAnswer(answer, testRecordId, questionId);
        }
        TestRecordDTO testRecordDTO = testRecordService.findOne(testRecordId);
        testRecordDTO.setQuestionTestRecord(testRecordService.findOneQuestionTestRecord(nextQuestionId, testRecordId));
        return ResultVO.ok(testRecordDTO);
    }


    @PostMapping("/evaluate")
    public ResultVO<TestRecord> evaluate(@RequestParam Long testRecordId,
                                         @RequestParam(required = false) Long questionId,
                                         @RequestParam(required = false) String answer) {
        if (StringUtils.hasText(answer)) {
            testRecordService.updateAnswer(answer, testRecordId, questionId);
        }
        return ResultVO.ok(testRecordService.evaluate(testRecordId));
    }

    @GetMapping("/list")
    public ResultVO<Page<TestRecord>> findAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                              @RequestParam(value = "size", defaultValue = "10") Integer size,
                                              @RequestParam(required = false) Integer status) {
        if (status == null) {
            return ResultVO.ok(testRecordService.findAll(new PageRequest(page,size)));
        }else {
            return ResultVO.ok(testRecordService.findAllByStatus(new PageRequest(page,size),status));
        }
    }


}
