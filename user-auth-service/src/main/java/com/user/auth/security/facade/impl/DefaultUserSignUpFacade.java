/**
 * Author: Vinod Jagwani
 */
package com.user.auth.security.facade.impl;

import com.google.common.collect.Lists;
import com.user.auth.feature.role.repository.entity.QUserRole;
import com.user.auth.feature.role.repository.entity.UserRole;
import com.user.auth.feature.role.service.UserRoleQueryService;
import com.user.auth.feature.user.repository.entity.User;
import com.user.auth.feature.user.service.UserCreateService;
import com.user.auth.security.dto.UserSignUpRequest;
import com.user.auth.security.dto.UserSignUpResponse;
import com.user.auth.security.facade.UserSignUpFacade;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.commons.collections4.ListUtils.emptyIfNull;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DefaultUserSignUpFacade implements UserSignUpFacade {

    ModelMapper modelMapper;

    UserCreateService userCreateService;

    UserRoleQueryService userRoleQueryService;

    @Override
    public UserSignUpResponse signUpUser(final UserSignUpRequest request) {
        log.trace("Start sign up user with request [{}]", request);
        final User savedUser = userCreateService.save(buildUser(request));
        final UserSignUpResponse response = modelMapper.map(savedUser, UserSignUpResponse.class);
        log.trace("End sign up user with userId [{}]", response.getUserId());
        return response;
    }

    private User buildUser(final UserSignUpRequest request) {
        final User user = modelMapper.map(request, User.class);
        user.setRoles(getUserRoles(request));
        return user;
    }

    private List<UserRole> getUserRoles(final UserSignUpRequest request) {
        final List<UserRole> response = Lists.newArrayList();
        emptyIfNull(request.getRoles()).forEach(userRole -> response.add(userRoleQueryService.findOne(QUserRole.userRole.name.eq(userRole.getName().toUpperCase())).getOrElseThrow(ex -> ex)));
        return response;
    }
}

