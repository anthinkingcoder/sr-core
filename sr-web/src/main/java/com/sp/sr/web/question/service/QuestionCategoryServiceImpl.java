package com.sp.sr.web.question.service;

import com.sp.sr.model.domain.question.QuestionCategory;
import com.sp.sr.model.dto.QuestionCategoryTreeDTO;
import com.sp.sr.model.repository.question.QuestionCategoryRepository;
import com.sp.sr.model.repository.question.QuestionRepository;
import com.sp.sr.model.service.QuestionCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author zhoulin
 */
@Service
public class QuestionCategoryServiceImpl implements QuestionCategoryService {

    @Autowired
    private QuestionCategoryRepository repository;

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<QuestionCategory> findAllByParentId(Long parentId) {
        return repository.findAllByDeleteAtIsNullAndParentId(parentId);
    }

    @Override
    public List<QuestionCategory> findAllByDepth(Integer depth) {
        return repository.findAllByDeleteAtIsNullAndDepth(depth);
    }

    @Override
    public Page<QuestionCategory> findAll(Pageable pageable) {
        return repository.findAllByDeleteAtIsNull(pageable);
    }

    @Override
    public QuestionCategory save(QuestionCategory questionCategory) {
        return repository.save(questionCategory);
    }

    @Override
    public List<QuestionCategoryTreeDTO> findTree() {
        List<QuestionCategory> roots = repository.findAllByDeleteAtIsNullAndDepth(0);
        List<QuestionCategory> children = repository.findAllByDeleteAtIsNullAndDepth(1);
        List<Object[]> questionNumList = questionRepository.countAllGroupByQuestionCategoryId();

        List<QuestionCategoryTreeDTO> questionCategoryTreeDTOS = new ArrayList<>(roots.size());
        for (QuestionCategory root : roots) {
            QuestionCategoryTreeDTO questionCategoryTreeDTO = new QuestionCategoryTreeDTO();
            questionCategoryTreeDTO.setQuestion(root);
            List<QuestionCategoryTreeDTO> sChildren = new ArrayList<>();
            for (QuestionCategory questionCategory : children) {
                QuestionCategoryTreeDTO q = new QuestionCategoryTreeDTO();
                q.setQuestion(questionCategory);
                q.setQuestionNum(0L);
                if (Objects.equals(questionCategory.getParentId(), root.getId())) {
                    for (Object[] item : questionNumList) {
                        if (Objects.equals((Long)item[1], questionCategory.getId())) {
                            q.setQuestionNum((Long) item[0]);
                        }
                    }
                    sChildren.add(q);

                }
            }
            questionCategoryTreeDTO.setChildren(sChildren);
            questionCategoryTreeDTOS.add(questionCategoryTreeDTO);
        }
        return questionCategoryTreeDTOS;
    }

    @Override
    public QuestionCategory findOne(Long id) {
        return repository.findByIdAndDeleteAtIsNull(id);
    }
}
