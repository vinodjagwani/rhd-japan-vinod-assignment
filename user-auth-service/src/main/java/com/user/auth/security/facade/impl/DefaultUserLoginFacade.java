/**
 * Author: Vinod Jagwani
 */
package com.user.auth.security.facade.impl;

import com.user.auth.feature.role.web.dto.UserRoleDetailResponse;
import com.user.auth.feature.user.repository.entity.User;
import com.user.auth.security.dto.UserLoginRequest;
import com.user.auth.security.dto.UserLoginResponse;
import com.user.auth.security.facade.UserLoginFacade;
import com.user.auth.security.util.JwtUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;
import static org.apache.commons.collections4.ListUtils.emptyIfNull;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DefaultUserLoginFacade implements UserLoginFacade {

    JwtUtils jwtUtils;

    ModelMapper modelMapper;

    AuthenticationManager authenticationManager;

    @Override
    public UserLoginResponse loginUser(final UserLoginRequest request) {
        log.trace("Start login user with username [{}] and email [{}]", request.getUsername(), request.getEmail());
        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final User user = ((User) authentication.getPrincipal());
        final String userEmail = ofNullable(user.getEmail()).filter(email -> request.getEmail().equals(user.getEmail()))
                .orElseThrow(() -> new BadCredentialsException("Invalid email or password"));
        final UserLoginResponse response = UserLoginResponse.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(userEmail)
                .mobile(user.getMobile())
                .lastName(user.getLastName())
                .firstName(user.getFirstName())
                .roles(emptyIfNull(user.getRoles()).stream().map(userRole -> modelMapper.map(userRole, UserRoleDetailResponse.class)).collect(Collectors.toList()))
                .accessToken(jwtUtils.generateJwtToken(authentication))
                .tokenType("Bearer").build();
        log.trace("End login user with username [{}] and email [{}]", user.getUserId(), user.getEmail());
        return response;
    }
}
