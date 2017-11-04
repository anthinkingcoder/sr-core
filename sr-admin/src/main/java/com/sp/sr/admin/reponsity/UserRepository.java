package com.sp.sr.admin.reponsity;

import com.sp.sr.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhoulin
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);

}
