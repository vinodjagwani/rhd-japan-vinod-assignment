/**
 * Author: Vinod Jagwani
 */
package com.user.auth.security.facade;

import com.user.auth.security.dto.UserSignUpRequest;
import com.user.auth.security.dto.UserSignUpResponse;

public interface UserSignUpFacade {

    UserSignUpResponse signUpUser(final UserSignUpRequest request);
}
