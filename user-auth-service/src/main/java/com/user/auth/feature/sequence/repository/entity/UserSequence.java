/**
 * Author: Vinod Jagwani
 */
package com.user.auth.feature.sequence.repository.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "user_sequences")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserSequence {

    @Id
    String id;

    String userIdPrefix;

    long seq;
}
