package com.sp.sr.admin.service;

import com.sp.sr.model.domain.User;
import com.sp.sr.model.dto.ExampleDTO;
import com.sp.sr.model.domain.example.Example;
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
