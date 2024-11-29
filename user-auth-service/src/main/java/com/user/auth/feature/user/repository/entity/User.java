/**
 * Author: Vinod Jagwani
 */
package com.user.auth.feature.user.repository.entity;

import com.user.auth.config.audit.Auditable;
import com.user.auth.feature.role.repository.entity.UserRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.collections4.ListUtils.emptyIfNull;


@Getter
@Setter
@Document("user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends Auditable<String> implements UserDetails {

    static final long serialVersionUID = -337991492884005133L;

    @MongoId
    String userId;

    @Version
    Long version;

    @Indexed(name = "IDX_UN_USER_USERNAME", unique = true)
    String username;

    String firstName;

    String lastName;

    @Indexed(name = "IDX_UN_USER_EMAIL", unique = true)
    String email;

    String mobile;

    String password;

    @DBRef
    List<UserRole> roles;

    boolean isEnabled = Boolean.TRUE;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return emptyIfNull(roles).stream().map(r -> new SimpleGrantedAuthority("ROLE_".concat(r.getName()))).collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }


}
