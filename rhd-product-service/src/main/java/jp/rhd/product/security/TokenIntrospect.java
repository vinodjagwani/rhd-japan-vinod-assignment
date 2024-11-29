/**
 * Author: Vinod Jagwani
 */
package jp.rhd.product.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Either;
import jp.rhd.product.exception.BusinessServiceException;
import jp.rhd.product.exception.dto.ErrorCodeEnum;
import jp.rhd.product.security.util.JwtUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.DefaultOAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.ReactiveOpaqueTokenIntrospector;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static java.util.Optional.ofNullable;
import static org.apache.commons.collections4.ListUtils.emptyIfNull;
import static org.apache.commons.lang3.BooleanUtils.isFalse;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TokenIntrospect implements ReactiveOpaqueTokenIntrospector {

    JwtUtils jwtUtils;

    ObjectMapper objectMapper;

    @Override
    public Mono<OAuth2AuthenticatedPrincipal> introspect(final String token) {
        final Either<BusinessServiceException, OAuth2AuthenticatedPrincipal> oAuth2AuthenticatedPrincipals = io.vavr.API.Match(token)
                .of(Case($(v -> isFalse(jwtUtils.validateJwtToken(token))), Either.left(new BusinessServiceException(ErrorCodeEnum.UNAUTHORIZED, "authentication.error.invalid_token"))),
                        Case($(), Either.right(authenticate(token, decode(token)))));
        return Mono.just(oAuth2AuthenticatedPrincipals.getOrElseThrow(ex -> ex));
    }

    @SneakyThrows
    private Map<String, Object> decode(final String token) {
        final DecodedJWT jwt = JWT.decode(token);
        final String base64EncodedBody = jwt.getPayload();
        final String body = new String(Base64.getUrlDecoder().decode(base64EncodedBody));
        final Map<String, Object> map = new ObjectMapper().readValue(body, new TypeReference<>() {
        });
        map.put("token", token);
        return map;
    }

    private OAuth2AuthenticatedPrincipal authenticate(final String token, final Map<String, Object> map) {
        final Instant tokenExpire = Instant.ofEpochSecond(Long.parseLong(ofNullable(map.get("exp")).orElse(0).toString()));
        final List<GrantedAuthority> authorities = emptyIfNull((List<Object>) map.getOrDefault("roles", null))
                .stream().map(Map.class::cast).map(r -> new SimpleGrantedAuthority("ROLE_".concat(r.get("name").toString()))).collect(Collectors.toList());
        final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(map.get("username").toString(), EMPTY, authorities);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        return new DefaultOAuth2AuthenticatedPrincipal(map.get("username").toString(), Map.of("exp", tokenExpire, "token", token), authorities);
    }

}
