package com.sp.sr.model.service.example;

import com.sp.sr.model.domain.user.User;
import com.sp.sr.model.domain.example.Example;
import com.sp.sr.model.dto.ExampleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author zhoulin
 */
public interface ExampleService {
    ExampleDTO save(User user, ExampleDTO exampleDTO);

    Page<Example> findAll(Pageable pageable);

    Page<Example> findAllByKnowledgeId(Pageable pageable, Long knowledgeId);

    ExampleDTO findOne(Long id);

    Example findById(Long id);

    void delete(Long id);

    List<Example> findAllByKnowledgeId(Long knowledgeId);

}
