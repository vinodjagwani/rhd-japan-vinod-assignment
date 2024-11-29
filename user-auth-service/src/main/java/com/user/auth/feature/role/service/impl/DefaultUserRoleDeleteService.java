/**
 * Author: Vinod Jagwani
 */
package com.user.auth.feature.role.service.impl;

import com.user.auth.feature.role.repository.UserRoleRepository;
import com.user.auth.feature.role.service.UserRoleDeleteService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DefaultUserRoleDeleteService implements UserRoleDeleteService {

    UserRoleRepository userRoleRepository;

    @Override
    public void delete(final String roleId) {
        log.trace("Start deleting user role with roleId [{}]", roleId);
        userRoleRepository.deleteById(roleId);
        log.trace("End deleting user role with roleId [{}]", roleId);
    }
}
