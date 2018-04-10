package com.sp.sr.admin.reponsity;

import com.sp.sr.model.domain.knowledge.Knowledge;
import com.sp.sr.model.enums.KnowledgeLevelEnum;
import com.sp.sr.model.repository.knowledge.KnowledgeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class KnowledgeRepositoryTest {
    @Autowired
    private KnowledgeRepository repository;

    @Test
    public void findByUploaderIdAndDeleteAtIsNullOrderBySortDesc() throws Exception {
    }

    @Test
    public void findAllByDeleteAtIsNull() throws Exception {
        Page<Knowledge> page = repository.findAllByLevelAndDeleteAtIsNull(new PageRequest(0,20),0);
        Assert.assertNotEquals(page.getSize(),0);
    }

    @Test
    public void findByParentIdAndDeleteAtIsNull() throws Exception {
    }

    @Test
    public void save() {
        Knowledge knowledge = new Knowledge();
        knowledge.setLevel(KnowledgeLevelEnum.BASE.getState());
        knowledge.setUploaderId(1L);
        knowledge.setName("Idea使用技巧");
        knowledge = repository.save(knowledge);
        Assert.assertNotNull(knowledge);
    }

}