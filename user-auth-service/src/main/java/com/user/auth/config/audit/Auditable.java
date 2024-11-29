/**
 * Author: Vinod Jagwani
 */
package com.user.auth.config.audit;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;


@Setter
@Getter
@MappedSuperclass

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Auditable<U extends Serializable> implements Serializable {


    @CreatedBy
    @Column(length = 36, updatable = false)
    U createdByUser;

    @CreatedDate
    @Column(length = 26, updatable = false)
    LocalDateTime createdDatetime;

    @LastModifiedBy
    @Column(length = 36)
    U updatedByUser;

    @Column(length = 20)
    LocalDateTime updatedDatetime;

}
