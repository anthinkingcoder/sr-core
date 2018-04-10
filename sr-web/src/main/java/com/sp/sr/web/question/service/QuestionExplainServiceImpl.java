package com.sp.sr.web.question.service;

import com.sp.sr.model.controller.BaseController;
import com.sp.sr.model.domain.question.explain.QuestionExplain;
import com.sp.sr.model.domain.question.explain.QuestionExplainAgree;
import com.sp.sr.model.domain.question.explain.QuestionExplainComment;
import com.sp.sr.model.domain.question.explain.QuestionExplainCommentAgree;
import com.sp.sr.model.enums.AgreeStatusEnum;
import com.sp.sr.model.enums.QuestionExplainCommendEnum;
import com.sp.sr.model.enums.ResultStatus;
import com.sp.sr.model.repository.question.explain.QuestionExplainAgreeRepository;
import com.sp.sr.model.repository.question.explain.QuestionExplainRepository;
import com.sp.sr.model.service.question.explain.QuestionExplainService;
import com.sp.sr.web.SrWebException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class QuestionExplainServiceImpl implements QuestionExplainService {

    @Autowired
    private QuestionExplainRepository questionExplainRepository;

    @Autowired
    private QuestionExplainAgreeRepository agreeRepository;


    @Override
    public QuestionExplain save(QuestionExplain questionExplain) {

        return questionExplainRepository.save(questionExplain);
    }

    @Override
    public Page<QuestionExplain> findAllByQuestionId(Pageable pageable, Long questionId, Integer editExplain) {
        Page<QuestionExplain> page = questionExplainRepository.findAllByDeleteAtIsNullAndQuestionIdAndCommendOrderByIdAsc(pageable, questionId, QuestionExplainCommendEnum.NORMAL.getState());
        List<QuestionExplain> questionExplains = new ArrayList<>(page.getContent());
        //将推荐解析放在首位
        QuestionExplain commendExplain = questionExplainRepository.findByCommendAndQuestionIdAndDeleteAtIsNull(QuestionExplainCommendEnum.COMMEND.getState(), questionId);
        if (commendExplain != null) {
            questionExplains.add(0, commendExplain);
            page = new PageImpl<>(questionExplains, pageable, page.getTotalElements());
        }
        //将本人解析放在首位
        if (editExplain != null && editExplain == 1) {
            //将需要显示的explainid放在首位
            QuestionExplain questionExplain = findOne(BaseController.USER.get().getId(), questionId);
            if (questionExplain != null) {
                //如果已有 则移除
                for (QuestionExplain qe : questionExplains) {
                    if (qe.getUserId().equals(BaseController.USER.get().getId())) {
                        questionExplains.remove(qe);
                        break;
                    }
                }
                questionExplains.add(0, questionExplain);
                page = new PageImpl<>(questionExplains, pageable, page.getTotalElements());
            }
        }
        return page;
    }

    @Override
    public QuestionExplain findById(Long id) {
        return questionExplainRepository.findByIdAndDeleteAtIsNull(id);
    }

    @Override
    public QuestionExplain findOne(Long userId, Long questionId) {
        return questionExplainRepository.findByQuestionIdAndUserIdAndDeleteAtIsNull(questionId, userId);
    }

    @Override
    public QuestionExplain commend(Long questionExplainId, Integer commend) {
        QuestionExplain questionExplain = questionExplainRepository.findByIdAndDeleteAtIsNull(questionExplainId);
        if (questionExplain == null) {
            throw new SrWebException(ResultStatus.RESOURCE_NOT_FOUND);
        }
        if (questionExplain.getCommend().equals(commend)) {
            return questionExplain;
        } else {
            questionExplain.setCommend(commend);
            questionExplain.setUpdateAt(new Date());
            questionExplainRepository.save(questionExplain);
        }
        return questionExplain;
    }

    @Override
    public void agree(Long questionExplainId, Integer agree) {
        QuestionExplainAgree questionExplainAgree = agreeRepository.findByUserIdAndQuestionExplainId(BaseController.USER.get().getId(), questionExplainId);
        if (questionExplainAgree == null) {
            questionExplainAgree = new QuestionExplainAgree();
            questionExplainAgree.setAgree(AgreeStatusEnum.LIKE.getState());
            questionExplainAgree.setUserId(BaseController.USER.get().getId());
            questionExplainAgree.setQuestionExplainId(questionExplainId);
        } else {
            if (questionExplainAgree.getAgree().equals(agree)) {
                return;
            }
            questionExplainAgree.setAgree(agree);
        }
        questionExplainAgree = agreeRepository.save(questionExplainAgree);
        if (questionExplainAgree.getAgree().equals(AgreeStatusEnum.DISLIE.getState())) {
            questionExplainRepository.decreaseAgree(questionExplainId);
        } else {
            questionExplainRepository.increaseAgree(questionExplainId);
        }
    }


}
