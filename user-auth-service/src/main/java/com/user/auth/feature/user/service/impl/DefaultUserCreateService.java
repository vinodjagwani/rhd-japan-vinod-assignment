/**
 * Author: Vinod Jagwani
 */
package com.user.auth.feature.user.service.impl;

import com.user.auth.feature.sequence.service.SequenceGeneratorService;
import com.user.auth.feature.user.repository.UserRepository;
import com.user.auth.feature.user.repository.entity.User;
import com.user.auth.feature.user.service.UserCreateService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DefaultUserCreateService implements UserCreateService {

    PasswordEncoder passwordEncoder;

    UserRepository userRepository;

    SequenceGeneratorService sequenceGeneratorService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User save(final User user) {
        log.trace("Start creating user with userId [{}]", user.getUserId());
        user.setUserId(sequenceGeneratorService.generateSequenceId());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        final User savedUser = userRepository.save(user);
        log.trace("End creating user with userId [{}]", savedUser.getUserId());
        return savedUser;
    }
}
