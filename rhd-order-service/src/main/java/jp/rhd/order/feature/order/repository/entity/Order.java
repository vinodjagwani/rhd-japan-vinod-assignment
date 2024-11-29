/**
 * Author: Vinod Jagwani
 */
package jp.rhd.order.feature.order.repository.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Setter
@Getter
@Table("rhd_order")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order implements Persistable<String> {

    @Id
    @Column
    String orderId;

    @Column
    String userId;

    @Column
    String firstName;

    @Column
    String lastName;

    @Column
    BigDecimal totalAmount;

    @Override
    public String getId() {
        return orderId;
    }

    @Override
    public boolean isNew() {
        return true;
    }
}

