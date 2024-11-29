/**
 * Author: Vinod Jagwani
 */
package com.user.auth.feature.role.service.impl;

import com.user.auth.feature.role.repository.UserRoleRepository;
import com.user.auth.feature.role.repository.entity.UserRole;
import com.user.auth.feature.role.service.UserRoleCreateService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DefaultUserRoleCreateService implements UserRoleCreateService {

    UserRoleRepository userRoleRepository;

    @Override
    public UserRole save(final UserRole userRole) {
        log.trace("Start creating user role with roleId [{}]", userRole.getRoleId());
        userRole.setName(userRole.getName().toUpperCase());
        final UserRole savedUserRole = userRoleRepository.save(userRole);
        log.trace("End creating user role with roleId [{}]", savedUserRole.getRoleId());
        return savedUserRole;
    }
}
