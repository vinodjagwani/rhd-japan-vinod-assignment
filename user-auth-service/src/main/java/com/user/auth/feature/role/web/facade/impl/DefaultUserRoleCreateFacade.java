/**
 * Author: Vinod Jagwani
 */
package com.user.auth.feature.role.web.facade.impl;


import com.user.auth.feature.role.repository.entity.UserRole;
import com.user.auth.feature.role.service.UserRoleCreateService;
import com.user.auth.feature.role.web.dto.UserRoleCreateRequest;
import com.user.auth.feature.role.web.dto.UserRoleCreateResponse;
import com.user.auth.feature.role.web.facade.UserRoleCreateFacade;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DefaultUserRoleCreateFacade implements UserRoleCreateFacade {

    ModelMapper modelMapper;

    UserRoleCreateService userRoleCreateService;

    @Override
    public UserRoleCreateResponse createUserRole(final UserRoleCreateRequest request) {
        log.trace("Start creating user role with request [{}]", request);
        final UserRole savedUserRole = userRoleCreateService.save(modelMapper.map(request, UserRole.class));
        UserRoleCreateResponse response = modelMapper.map(savedUserRole, UserRoleCreateResponse.class);
        log.trace("End creating user role with response [{}]", response);
        return response;
    }
}
