/**
 * Author: Vinod Jagwani
 */
package com.user.auth.feature.role.repository;

import com.user.auth.feature.role.repository.entity.UserRole;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface UserRoleRepository extends MongoRepository<UserRole, String>, QuerydslPredicateExecutor<UserRole> {
}
