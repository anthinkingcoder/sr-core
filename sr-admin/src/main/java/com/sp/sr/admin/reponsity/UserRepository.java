package com.sp.sr.admin.reponsity;

import com.sp.sr.model.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhoulin
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsernameAndDeleteAtIsNull(String username);

    Page<User> findAllByLevelAndDeleteAtIsNull(Integer level, Pageable pageable);

    User findByIdAndDeleteAtIsNull(Long id);

}
