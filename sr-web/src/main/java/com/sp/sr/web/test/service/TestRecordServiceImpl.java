package com.sp.sr.web.test.service;

import com.sp.sr.model.controller.BaseController;
import com.sp.sr.model.domain.test.QuestionRecord;
import com.sp.sr.model.domain.question.Question;
import com.sp.sr.model.domain.test.TestRecord;
import com.sp.sr.model.dto.QuestionDTO;
import com.sp.sr.model.dto.TestRecordDTO;
import com.sp.sr.model.enums.QuestionOriginEnum;
import com.sp.sr.model.enums.ResultStatus;
import com.sp.sr.model.enums.TestRecordStatus;
import com.sp.sr.model.repository.question.QuestionCategoryRepository;
import com.sp.sr.model.repository.question.QuestionRecordRepository;
import com.sp.sr.model.repository.question.QuestionRepository;
import com.sp.sr.model.repository.test.TestRecordRepository;
import com.sp.sr.model.service.TestRecordService;
import com.sp.sr.model.util.Strings;
import com.sp.sr.web.SrWebException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

import static com.sp.sr.model.controller.BaseController.USER;

@Service
@Slf4j
public class TestRecordServiceImpl implements TestRecordService {


    @Autowired
    private TestRecordRepository testRecordRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionRecordRepository questionRecordRepository;

    @Autowired
    private QuestionCategoryRepository questionCategoryRepository;


    @Override
    @Transactional
    public TestRecordDTO create(TestRecordDTO testRecordDTO) {
        //根据categoryIds分批获取该类别下的题目
        List<Long> ids = new ArrayList<>(testRecordDTO.getCategoryIds().length);
        for (String s : testRecordDTO.getCategoryIds()) {
            ids.add(Long.valueOf(s));
        }
        TestRecord testRecord = new TestRecord();
        testRecord.setStatus(TestRecordStatus.UN_FINISH.getCode());
        testRecord.setQuestionNum(testRecordDTO.getQuestionNum());
        testRecord.setLevel(testRecordDTO.getLevel());
        testRecord.setOrigin(testRecordDTO.getOrigin());
        testRecord.setStudentId(USER.get().getId());
        testRecord.setStartTime(new Date());
        int totalQuestionNum;
        //获取指定题目类别编号以及难度级别的题目集合
        List<Long> sourceQuestionIds = questionRepository.findIdsByQuestionCategoryIdInAndLevel(ids, testRecord.getLevel());
        //根据origin筛选题目
        List<QuestionRecord> questionRecords = questionRecordRepository.findAllByQuestionCategoryIdInAndStudentId(ids, testRecord.getStudentId());
        sourceQuestionIds = filterByOrigin(testRecord.getOrigin(), sourceQuestionIds, questionRecords);
        log.info("sourceQuestionIds = {}",sourceQuestionIds.toString());
        //检查符合条件的题目个数
        totalQuestionNum = sourceQuestionIds.size();
        if (totalQuestionNum < testRecord.getQuestionNum()) {
            throw new SrWebException(ResultStatus.QUESTION_NUM_NOT);
        }
        //洗牌算法获取问题路径
        testRecord.setQuestionPath(getQuestionPath(sourceQuestionIds, testRecord.getQuestionNum()));

        //初始化回答路径和结果路径
        StringBuilder answerPath = new StringBuilder();
        for (int i = 0; i < testRecord.getQuestionNum(); i++) {
            if (answerPath.length() > 0) {
                answerPath.append("/");
            }
            answerPath.append(-1);
        }
        testRecord.setAnswerPath(answerPath.toString());
        testRecord.setResultPath(testRecord.getAnswerPath().replace("/", ","));
        //保存记录
        testRecord = testRecordRepository.save(testRecord);


        testRecordDTO.setTestRecordId(testRecord.getId());
        return testRecordDTO;

    }


    /**
     * 根据洗牌算法从指定问题列表中选取指定数量的问题列表 拼接成问题路径,
     *
     * @param sourceQuestionIds 问题列表
     * @param questionNum       问题数量
     * @return 问题路径 比如 questionNum = 5 return "1,3,4,2,6"
     */
    public String getQuestionPath(List<Long> sourceQuestionIds, Integer questionNum) {
        StringBuilder questionPath = new StringBuilder();
        int questionCount = 0;
        Random random = new Random(System.currentTimeMillis());
        int[] flags = new int[sourceQuestionIds.size()];
        for (; ; ) {
            int index = random.nextInt(sourceQuestionIds.size());
            Long questionId = sourceQuestionIds.get(index);
            if (flags[index] == 0) {
                if (questionPath.length() > 0) {
                    questionPath.append(",");
                }
                questionPath.append(questionId);
                questionCount++;
                flags[index] = 1;
            }
            if (questionCount == questionNum) {
                break;
            }
        }
        return questionPath.toString();
    }

    /**
     * 根据出题来源过滤题目
     *
     * @param origin          出题来源
     * @param questionIds     原题目id集合
     * @param questionRecords 已做题目记录集合
     * @return 过滤之后的题目集合
     */
    public List<Long> filterByOrigin(Integer origin, List<Long> questionIds, List<QuestionRecord> questionRecords) {
        if (origin.equals(QuestionOriginEnum.NEW.getState())) {
            List<Long> filterIds = getIds(QuestionOriginEnum.NEW, questionRecords);
            log.info("filterIds = {}",filterIds.toString());
            return questionIds.stream().filter((id) -> !filterIds.contains(id)).collect(Collectors.toList());
        } else if (origin.equals(QuestionOriginEnum.WRONG.getState())) {
            List<Long> filterIds = getIds(QuestionOriginEnum.WRONG, questionRecords);
            return questionIds.stream().filter(filterIds::contains).collect(Collectors.toList());
        } else if (origin.equals(QuestionOriginEnum.NEW_AND_WRONG.getState())) {
            List<Long> filterIds = getIds(QuestionOriginEnum.NEW_AND_WRONG, questionRecords);
            return questionIds.stream().filter((id) -> !filterIds.contains(id)).collect(Collectors.toList());
        } else {
            return questionIds;
        }
    }

    /**
     * 根据出题来源获取需要过滤的题目id集合
     *
     * @param questionOriginEnum 出题来源枚举常量
     * @param questionRecords    已做题目记录集合
     * @return 需要过滤的题目id集合
     */
    public List<Long> getIds(QuestionOriginEnum questionOriginEnum, List<QuestionRecord> questionRecords) {
        List<String> ids = new ArrayList<>();
        log.info("questionRecord = {}",questionRecords);
        for (QuestionRecord questionRecord : questionRecords) {
            if (questionOriginEnum == QuestionOriginEnum.NEW) {
                log.info("path = {}",questionRecord.getPath());
                if (StringUtils.hasText(questionRecord.getPath())) {
                    String[] questionIds = questionRecord.getPath().split(",");
                    ids.addAll(Arrays.asList(questionIds));
                }
            } else if (questionOriginEnum == QuestionOriginEnum.WRONG) {
                if (StringUtils.hasText(questionRecord.getPath())) {
                    String[] questionIds = questionRecord.getPath().split(",");
                    String[] results = questionRecord.getPath().split(",");
                    for (int i = 0; i < questionIds.length; i++) {
                        if ("2".equals(results[i])) {
                            ids.add(questionIds[i]);
                        }
                    }
                }
            } else if (questionOriginEnum == QuestionOriginEnum.NEW_AND_WRONG) {
                if (StringUtils.hasText(questionRecord.getPath())) {
                    String[] questionIds = questionRecord.getPath().split(",");
                    String[] results = questionRecord.getPath().split(",");
                    for (int i = 0; i < questionIds.length; i++) {
                        if ("1".equals(results[i])) {
                            ids.add(questionIds[i]);
                        }
                    }
                }
            }
        }
        return ids.stream().mapToLong(Long::valueOf).boxed().collect(Collectors.toList());
    }


    @Override
    public TestRecordDTO findOne(Long testRecordId) {
        TestRecord testRecord = testRecordRepository.findOne(testRecordId);
        if (testRecord == null) {
            throw new SrWebException(ResultStatus.RESOURCE_NOT_FOUND);
        }
        if (!Objects.equals(testRecord.getStudentId(), BaseController.USER.get().getId())) {
            throw new SrWebException(ResultStatus.PERMISSION_ERROR);
        }
        TestRecordDTO testRecordDTO = new TestRecordDTO(
                testRecordId,
                testRecord.getLevel(),
                testRecord.getQuestionNum(),
                testRecord.getQuestionRightNum(),
                testRecord.getOrigin(),
                testRecord.getAnswerPath(),
                testRecord.getResultPath(),
                testRecord.getStartTime(),
                testRecord.getScore(),
                testRecord.getStatus(),
                testRecord.getStudentId());
        testRecordDTO.setEndTime(testRecord.getEndTime());
        String[] questionIds = testRecord.getQuestionPath().split(",");
        testRecordDTO.setQuestionIds(Arrays.stream(questionIds).mapToLong(Long::valueOf).boxed().collect(Collectors.toList()));
        return testRecordDTO;
    }

    @Override
    public QuestionDTO findOneQuestionTestRecord(Long questionId, Long testRecordId) {
        TestRecord testRecord = testRecordRepository.findOne(testRecordId);
        if (testRecord == null) {
            throw new SrWebException(ResultStatus.RESOURCE_NOT_FOUND);
        }

        Question question = questionRepository.findOne(questionId);
        if (question == null) {
            throw new SrWebException(ResultStatus.RESOURCE_NOT_FOUND);
        }
        String[] questionIds = testRecord.getQuestionPath().split(",");
        String[] answers = testRecord.getAnswerPath().split("/");
        String[] results = testRecord.getResultPath().split(",");
        QuestionDTO questionDTO = new QuestionDTO();
        if (!Objects.equals(testRecord.getStatus(), TestRecordStatus.FINISH.getCode())) {
            question.setAnswer("");
        }
        questionDTO.setQuestion(question);
        for (int i = 0; i < questionIds.length; i++) {
            if (question.getId().equals(Long.valueOf(questionIds[i]))) {
                if ("-1".equals(answers[i])) {
                    questionDTO.setDone(false);
                } else {
                    questionDTO.setAnswer(answers[i]);
                    questionDTO.setDone(true);
                    if ("1".equals(results[i])) {
                        questionDTO.setRight(true);
                    } else {
                        questionDTO.setRight(false);
                    }
                }
                break;
            }
        }
        if (questionDTO.getAnswer() == null) {
            questionDTO.setAnswer("");
        }
        return questionDTO;
    }

    @Override
    public TestRecord updateAnswer(String answer, Long testRecordId, Long questionId) {
        TestRecord testRecord = testRecordRepository.findOne(testRecordId);
        if (testRecord == null) {
            throw new SrWebException(ResultStatus.RESOURCE_NOT_FOUND);
        }
        if (Objects.equals(testRecord.getStatus(), TestRecordStatus.FINISH.getCode())) {
            throw new SrWebException(ResultStatus.REPEAT_SAVE);
        }
        String[] answers = testRecord.getAnswerPath().split("/");
        String[] questionIds = testRecord.getQuestionPath().split(",");
        for (int i = 0; i < questionIds.length; i++) {
            if (Objects.equals(Long.valueOf(questionIds[i]), questionId)) {
                answers[i] = Strings.orderAsc(answer,",");
            }
        }
        StringBuilder answerPath = new StringBuilder();
        for (String s : answers) {
            if (answerPath.length() > 0) {
                answerPath.append("/");
            }
            answerPath.append(s);
        }
        testRecord.setAnswerPath(answerPath.toString());
        return testRecordRepository.save(testRecord);
    }

    @Override
    public TestRecord evaluate(Long testRecordId) {
        TestRecord testRecord = testRecordRepository.findOne(testRecordId);
        if (testRecord == null) {
            throw new SrWebException(ResultStatus.RESOURCE_NOT_FOUND);
        }
        if (Objects.equals(testRecord.getStatus(), TestRecordStatus.FINISH.getCode())) {
            throw new SrWebException(ResultStatus.REPEAT_SAVE);
        }
        String[] answers = testRecord.getAnswerPath().split("/");
        String[] results = testRecord.getResultPath().split(",");
        String[] questionIds = testRecord.getQuestionPath().split(",");
        List<Question> questions = questionRepository.findAllByIdIn(Arrays.stream(questionIds).mapToLong(Long::valueOf).boxed().collect(Collectors.toList()));
        if (questions.size() != testRecord.getQuestionNum()) {
            throw new SrWebException(ResultStatus.SERVICE_ERROR);
        }
        int questionRightNum = 0;
        for (Question question : questions) {
            for (int i = 0; i < questionIds.length; i++) {
                if (question.getId().equals(Long.valueOf(questionIds[i]))) {
                    if (!StringUtils.hasText(answers[i])) {
                        results[i] = "-1";
                    } else {
                        if (answers[i].equals(question.getAnswer())) {
                            results[i] = "1";
                            questionRightNum++;
                        } else {
                            results[i] = "2";
                        }
                    }
                    break;
                }
            }
        }
        testRecord.setResultPath(Strings.concat(results, ","));
        log.info("resultPath = {}", testRecord.getResultPath());
        testRecord.setEndTime(new Date());
        testRecord.setQuestionRightNum(questionRightNum);
        float rate = testRecord.getQuestionRightNum() / (float) (testRecord.getQuestionNum());
        float score = rate * 100;
        testRecord.setScore((int) score);
        testRecord.setStatus(TestRecordStatus.FINISH.getCode());
        testRecord = testRecordRepository.save(testRecord);
        //异步更新做题记录,方便出题来源过滤
        try{
            updateQuestionRecord(testRecord);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return testRecord;
    }

    @Override
    public Page<TestRecord> findAll(Pageable pageable) {
        return testRecordRepository.findAllByDeleteAtIsNullOrderByIdDesc(pageable);
    }

    @Override
    public Page<TestRecord> findAllByStatus(Pageable pageable, Integer status) {
        return testRecordRepository.findAllByStatusAndDeleteAtIsNullOrderByEndTimeDesc(pageable,status);
    }


    /**
     * 异步更新个人题目记录
     * @param testRecord 更新源 当前测试
     */
    @Async
    @Transactional(rollbackFor = Exception.class)
    public void updateQuestionRecord(TestRecord testRecord) {
        List<String> questionIds = Arrays.asList(testRecord.getQuestionPath().split(","));
        String[] results = testRecord.getResultPath().split(",");
        List<Question> questions = questionRepository.findAllByIdIn(questionIds.stream().mapToLong(Long::valueOf).boxed().collect(Collectors.toList()));
        List<Long> categoryIds = new ArrayList<>();
        for (Question question : questions) {
            if (!categoryIds.contains(question.getQuestionCategoryId())) {
                categoryIds.add(question.getQuestionCategoryId());
            }
        }
        List<QuestionRecord> questionRecords = questionRecordRepository.findAllByQuestionCategoryIdInAndStudentId(categoryIds, testRecord.getStudentId());
        for (Question question : questions) {
            int i;
            if ((i = questionIds.indexOf(String.valueOf(question.getId()))) != -1) {
                boolean isExist = false;
                for (QuestionRecord questionRecord : questionRecords) {
                    if (questionRecord.getQuestionCategoryId().equals(question.getQuestionCategoryId())) {
                        isExist = true;
                        if (!StringUtils.hasText(questionRecord.getPath())) {
                            break;
                        }
                        List<String> qIds = new ArrayList<>(Arrays.asList(questionRecord.getPath().split(",")));
                        List<String> qResults = new ArrayList<>(Arrays.asList(questionRecord.getResultPath().split(",")));
                        int index;
                        if ((index = qIds.indexOf(String.valueOf(question.getId()))) != -1) {
                            if (!qResults.get(index).equals(results[i])) {
                                qResults.set(index, results[i]);
                            }
                        } else {
                            qResults.add(results[i]);
                            qIds.add(String.valueOf(question.getId()));
                            questionRecord.setQuestionNum(questionRecord.getQuestionNum() + 1);
                        }
                        questionRecord.setPath(Strings.concat(qIds.toArray(new String[qIds.size()]), ","));
                        questionRecord.setResultPath(Strings.concat(qResults.toArray(new String[qResults.size()]), ","));
                    }
                }
                if (!isExist) {
                    QuestionRecord questionRecord = new QuestionRecord();
                    questionRecord.setPath(String.valueOf(question.getId()));
                    questionRecord.setResultPath(results[i]);
                    questionRecord.setQuestionCategoryId(question.getQuestionCategoryId());
                    questionRecord.setQuestionNum(1);
                    questionRecord.setStudentId(testRecord.getStudentId());
                    questionRecords.add(questionRecord);
                }
            }

        }
        //批量保存
        questionRecordRepository.save(questionRecords);
    }
}
