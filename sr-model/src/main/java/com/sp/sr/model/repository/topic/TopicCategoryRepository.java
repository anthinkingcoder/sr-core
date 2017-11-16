package com.sp.sr.model.repository.topic;

import com.sp.sr.model.domain.topic.TopicCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicCategoryRepository extends JpaRepository<TopicCategory, Long> {
    List<TopicCategory> findAllByDeleteAtIsNullOrderBySort();

    TopicCategory save(TopicCategory topicCategory);

    TopicCategory findByIdAndDeleteAtIsNull(Long id);


}
