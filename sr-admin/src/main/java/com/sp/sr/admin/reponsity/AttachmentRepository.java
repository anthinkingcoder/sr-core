package com.sp.sr.admin.reponsity;

import com.sp.sr.model.domain.Attachment;
import com.sp.sr.model.domain.ResourceDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zhoulin
 */
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
    Attachment findByIdAndDeleteAtIsNull(Long aLong);

    List<Attachment> findAllByModuleCategoryAndModuleIdAndDeleteAtIsNull(Integer moduleCategory, Long moduleId);


    List<Attachment> findAllByModuleCategoryAndModuleIdAndUploaderIdAndDeleteAtIsNull(Integer moduleCategory, Long moduleId, Long uploaderId);


}
