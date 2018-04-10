package com.sp.sr.web.responsity;

import com.sp.sr.model.repository.question.QuestionRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class QuestionRepositoryTest {
    @Autowired
    private QuestionRepository questionRepository;
    @Test
    public void findIdByDeleteAtIsNullAndQuestionCategoryIdIn() throws Exception {
    }

    @Test
    public void countAllGroupByQuestionCategoryId() {
//        log.info(questionRepository.countAllGroupByQuestionCategoryId().toString());

    }

}