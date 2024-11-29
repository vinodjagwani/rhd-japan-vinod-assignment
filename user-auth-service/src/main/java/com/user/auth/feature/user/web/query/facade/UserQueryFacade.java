/**
 * Author: Vinod Jagwani
 */
package com.user.auth.feature.user.web.query.facade;

import com.querydsl.core.types.Predicate;
import com.user.auth.feature.user.web.query.dto.UserQueryDetailResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserQueryFacade {

    UserQueryDetailResponse findUser(final Predicate predicate);

    Page<UserQueryDetailResponse> findAllUsers(final Predicate predicate, final Pageable pageable);

}
