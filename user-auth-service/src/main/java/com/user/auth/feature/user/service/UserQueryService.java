/**
 * Author: Vinod Jagwani
 */
package com.user.auth.feature.user.service;

import com.querydsl.core.types.Predicate;
import com.user.auth.exception.UserAuthServiceException;
import com.user.auth.feature.user.repository.entity.User;
import io.vavr.control.Either;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserQueryService {

    Page<User> findAll(final Predicate predicate, final Pageable pageable);

    Either<UserAuthServiceException, User> findOne(final Predicate predicate);

}
