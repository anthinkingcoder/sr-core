package com.sp.sr.model.service.question;

import com.sp.sr.model.domain.question.QuestionCategory;
import com.sp.sr.model.dto.QuestionCategoryTreeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author zhoulin
 */
public interface QuestionCategoryService {
    List<QuestionCategory> findAllByParentId(Long parentId);

    List<QuestionCategory> findAllByDepth(Integer depth);

    Page<QuestionCategory> findAll(Pageable pageable);

    QuestionCategory save(QuestionCategory questionCategory);

    List<QuestionCategoryTreeDTO> findTree();

    QuestionCategory findOne(Long id);

}
