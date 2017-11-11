package com.sp.sr.model.service;

import com.sp.sr.model.domain.User;
import com.sp.sr.model.domain.example.Example;
import com.sp.sr.model.dto.ExampleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author zhoulin
 */
public interface ExampleService {
    ExampleDTO save(User user, ExampleDTO exampleDTO);

    Page<Example> findAll(Pageable pageable);

    ExampleDTO findOne(Long id);

    Example findById(Long id);

    void delete(Long id);

}
