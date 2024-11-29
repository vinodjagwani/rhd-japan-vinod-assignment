/**
 * Author: Vinod Jagwani
 */
package com.user.auth.feature.user.repository;

import com.user.auth.feature.user.repository.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface UserRepository extends MongoRepository<User, String>, QuerydslPredicateExecutor<User> {
}
