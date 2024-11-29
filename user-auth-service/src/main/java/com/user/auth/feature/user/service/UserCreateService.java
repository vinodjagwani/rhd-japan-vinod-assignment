/**
 * Author: Vinod Jagwani
 */
package com.user.auth.feature.user.service;

import com.user.auth.feature.user.repository.entity.User;

public interface UserCreateService {

    User save(final User user);
}
