/**
 * Author: Vinod Jagwani
 */
package com.user.auth.security.facade;


import com.user.auth.security.dto.UserLoginRequest;
import com.user.auth.security.dto.UserLoginResponse;

public interface UserLoginFacade {

    UserLoginResponse loginUser(final UserLoginRequest request);
}
