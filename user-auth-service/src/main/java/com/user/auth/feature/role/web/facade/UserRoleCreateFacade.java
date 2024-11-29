/**
 * Author: Vinod Jagwani
 */
package com.user.auth.feature.role.web.facade;

import com.user.auth.feature.role.web.dto.UserRoleCreateRequest;
import com.user.auth.feature.role.web.dto.UserRoleCreateResponse;

public interface UserRoleCreateFacade {

    UserRoleCreateResponse createUserRole(final UserRoleCreateRequest request);
}
