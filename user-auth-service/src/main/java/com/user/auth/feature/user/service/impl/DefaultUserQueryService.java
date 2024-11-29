/**
 * Author: Vinod Jagwani
 */
package com.user.auth.feature.user.service.impl;

import com.querydsl.core.types.Predicate;
import com.user.auth.exception.UserAuthServiceException;
import com.user.auth.exception.constant.ErrorCodeEnum;
import com.user.auth.feature.user.repository.UserRepository;
import com.user.auth.feature.user.repository.entity.User;
import com.user.auth.feature.user.service.UserQueryService;
import io.vavr.control.Either;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DefaultUserQueryService implements UserQueryService {

    UserRepository userRepository;

    @Override
    public Page<User> findAll(final Predicate predicate, final Pageable pageable) {
        log.trace("Start querying all users with predicate [{}] and pageable [{}]", predicate, pageable);
        final Page<User> users = userRepository.findAll(predicate, pageable);
        log.trace("End querying all users with no of records [{}]", users.getSize());
        return users;
    }

    @Override
    @Transactional(readOnly = true)
    public Either<UserAuthServiceException, User> findOne(final Predicate predicate) {
        log.trace("Start querying user with predicate [{}]", predicate);
        final Optional<User> optionalUser = userRepository.findOne(predicate);
        return Match(optionalUser).of(Case($(v -> optionalUser.isPresent()), Either.right(optionalUser.orElse(null))),
                Case($(), Either.left(new UserAuthServiceException(ErrorCodeEnum.ENTITY_NOT_FOUND, "user.error.entity_not_found"))));
    }
}
