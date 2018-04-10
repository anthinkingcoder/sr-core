package com.sp.sr.web.example.service;

import com.sp.sr.admin.common.Auths;
import com.sp.sr.model.domain.user.User;
import com.sp.sr.model.domain.example.Example;
import com.sp.sr.model.domain.example.ExampleDetail;
import com.sp.sr.model.dto.ExampleDTO;
import com.sp.sr.model.enums.ResultStatus;
import com.sp.sr.model.repository.example.ExampleDetailRepository;
import com.sp.sr.model.repository.example.ExampleRepository;
import com.sp.sr.model.service.example.ExampleService;
import com.sp.sr.web.SrWebException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.sp.sr.model.controller.BaseController.USER;


/**
 * @author zhoulin
 */
@Service
@Slf4j
public class ExampleServiceImpl implements ExampleService {

    @Autowired
    private ExampleRepository exampleRepository;

    @Autowired
    private ExampleDetailRepository exampleDetailRepository;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public ExampleDTO save(User user, ExampleDTO exampleDTO) {
        return null;
    }

    @Override
    public Page<Example> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Example> findAllByKnowledgeId(Pageable pageable, Long knowledgeId) {
        if (Auths.isSystem(USER.get())) {
            return exampleRepository.findAllByDeleteAtIsNullAndKnowledgeIdOrderByCreateAtAsc(pageable, knowledgeId);
        }
        return exampleRepository.findAllByDeleteAtIsNullAndUploaderIdAndKnowledgeIdOrderByCreateAtAsc(pageable, USER.get().getId(), knowledgeId);
    }

    @Override
    public ExampleDTO findOne(Long id) {
        Example example = exampleRepository.findOne(id);
        if (example == null) {
            throw new SrWebException(ResultStatus.RESOURCE_NOT_FOUND);
        }
        ExampleDetail exampleDetail = exampleDetailRepository.findOne(example.getExampleDetailId());
        if (exampleDetail == null) {
            throw new SrWebException(ResultStatus.RESOURCE_NOT_FOUND);
        }
        ExampleDTO exampleDTO = new ExampleDTO();
        BeanUtils.copyProperties(exampleDetail, exampleDTO);
        BeanUtils.copyProperties(example, exampleDTO);
        return exampleDTO;
    }


    @Override
    public Example findById(Long id) {
        return exampleRepository.findByIdAndDeleteAtIsNull(id);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Example> findAllByKnowledgeId(Long knowledgeId) {
        return exampleRepository.findAllByKnowledgeIdAndDeleteAtIsNullOrderByLevel(knowledgeId);
    }


}
