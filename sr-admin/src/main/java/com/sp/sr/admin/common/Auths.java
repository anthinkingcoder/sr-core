package com.sp.sr.admin.common;

import com.sp.sr.model.domain.user.User;
import com.sp.sr.model.enums.ResultStatus;
import com.sp.sr.model.enums.RoleCategoryEnum;
import java.util.Objects;

public class Auths {
    public static void verityRole(User currentUser, RoleCategoryEnum... roleCategoryEnums) {
        for (RoleCategoryEnum roleCategoryEnum : roleCategoryEnums) {
            if (roleCategoryEnum.getState().equals(currentUser.getLevel())) {
                return;
            }
        }
        throw new SrAdminException(ResultStatus.PERMISSION_ERROR);
    }

    public static void verityUploader(User currentUser, Long uploaderId) {
        if (RoleCategoryEnum.SYSTEM.getState().equals(currentUser.getLevel())) {
            return;
        }
        if (RoleCategoryEnum.TEACHER.getState().equals(currentUser.getLevel())) {
            if (Objects.equals(currentUser.getId(), uploaderId)) {
                return;
            }
        }
        throw new SrAdminException(ResultStatus.PERMISSION_ERROR);
    }

    /**
     * 验证用户是否是系统管理员
     *
     * @param user user
     * @return true or false
     */
    public static boolean isSystem(User user) {
        return RoleCategoryEnum.SYSTEM.getState().equals(user.getLevel());
    }
}
