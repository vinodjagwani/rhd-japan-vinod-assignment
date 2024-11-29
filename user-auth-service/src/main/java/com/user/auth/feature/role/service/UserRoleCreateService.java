/**
 * Author: Vinod Jagwani
 */
package com.user.auth.feature.role.service;

import com.user.auth.feature.role.repository.entity.UserRole;

public interface UserRoleCreateService {

    UserRole save(final UserRole userRole);
}
