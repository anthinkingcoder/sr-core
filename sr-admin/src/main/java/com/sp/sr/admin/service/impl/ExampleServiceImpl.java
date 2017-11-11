package com.sp.sr.admin.service.impl;

import com.sp.sr.admin.Auths;
import com.sp.sr.admin.SrAdminException;
import com.sp.sr.model.repository.ExampleDetailRepository;
import com.sp.sr.model.repository.ExampleRepository;
import com.sp.sr.model.service.ExampleService;
import com.sp.sr.model.domain.User;
import com.sp.sr.model.domain.example.Example;
import com.sp.sr.model.domain.example.ExampleDetail;
import com.sp.sr.model.dto.ExampleDTO;
import com.sp.sr.model.enums.ResultStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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
            Example example;
            ExampleDetail exampleDetail;
            if (exampleDTO.getId() != null && exampleDTO.getId() != 0) {
                example = exampleRepository.findOne(exampleDTO.getId());
                if (example == null) {
                    throw new SrAdminException(ResultStatus.ARGUMENT_ERROR);
                }
                Auths.verityUploader(user, example.getUploaderId());
                exampleDetail = exampleDetailRepository.findOne(example.getExampleDetailId());
                if (exampleDetail == null) {
                    throw new SrAdminException(ResultStatus.ARGUMENT_ERROR);
                }
            } else {
                example = new Example();
                exampleDetail = new ExampleDetail();
                example.setUploaderId(exampleDTO.getUploaderId());
            }

            example.setKnowledgeId(exampleDTO.getKnowledgeId());
            example.setTitle(exampleDTO.getTitle());
            example.setTopicId(exampleDTO.getTopicId());


            exampleDetail.setContent(exampleDTO.getContent());
            exampleDetail.setRuntimeResult(exampleDTO.getRuntimeResult());
            exampleDetail.setExampleExplain(exampleDTO.getExampleExplain());
            exampleDetail = exampleDetailRepository.save(exampleDetail);
            if (exampleDetail != null && example.getId() == null) {
                example.setExampleDetailId(exampleDetail.getId());
            }
            exampleRepository.save(example);
            return exampleDTO;
    }

    @Override
    public Page<Example> findAll(Pageable pageable) {
        if (Auths.isSystem(USER.get())) {
            return exampleRepository.findAllByDeleteAtIsNullOrderByCreateAtAsc(pageable);
        }
        return exampleRepository.findAllByDeleteAtIsNullAndUploaderIdOrderByCreateAtAsc(pageable,USER.get().getId());

    }

    @Override
    public ExampleDTO findOne(Long id) {
        Example example = exampleRepository.findOne(id);
        if (example == null) {
            throw new SrAdminException(ResultStatus.RESOURCE_NOT_FOUND);
        }
        ExampleDetail exampleDetail = exampleDetailRepository.findOne(example.getExampleDetailId());
        if (exampleDetail == null) {
            throw new SrAdminException(ResultStatus.RESOURCE_NOT_FOUND);
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
        Example example = exampleRepository.findOne(id);
        if (example == null) {
            throw new SrAdminException(ResultStatus.RESOURCE_NOT_FOUND);
        }
        example.setDeleteAt(new Date());
        exampleRepository.save(example);
        ExampleDetail exampleDetail = exampleDetailRepository.findOne(example.getExampleDetailId());
        if (exampleDetail != null) {
            exampleDetail.setDeleteAt(new Date());
            exampleDetailRepository.save(exampleDetail);
        }
    }
}
