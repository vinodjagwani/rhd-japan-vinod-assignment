/**
 * Author: Vinod Jagwani
 */
package com.user.auth.feature.role.repository.entity;


import com.user.auth.config.audit.Auditable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.UUID;

@Getter
@Setter
@Document("user_role")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRole extends Auditable<String> {

    static final long serialVersionUID = -787991392884005033L;

    @MongoId
    String roleId = UUID.randomUUID().toString();

    @Indexed(name = "IDX_UN_USER_ROLE_NAME", unique = true)
    String name;
}
