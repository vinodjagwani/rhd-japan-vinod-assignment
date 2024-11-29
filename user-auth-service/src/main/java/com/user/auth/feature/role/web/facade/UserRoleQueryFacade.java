/**
 * Author: Vinod Jagwani
 */
package com.user.auth.feature.role.web.facade;

import com.querydsl.core.types.Predicate;
import com.user.auth.feature.role.web.dto.UserRoleDetailResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRoleQueryFacade {

    UserRoleDetailResponse queryUserRole(final Predicate predicate);

    Page<UserRoleDetailResponse> queryAllUserRoles(final Predicate predicate, final Pageable pageable);
}
