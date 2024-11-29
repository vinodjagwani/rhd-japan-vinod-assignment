/**
 * Author: Vinod Jagwani
 */
package jp.rhd.product.feature.item.repository.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Setter
@Getter
@Table("item")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Item implements Persistable<String> {

    @Id
    @Column
    String itemId;

    @Column
    String name;

    @Column
    BigDecimal totalPrice = BigDecimal.ZERO;

    @Column
    String description;

    @Column
    String imageId;

    @Override
    public String getId() {
        return itemId;
    }

    @Override
    @Transient
    public boolean isNew() {
        return true;
    }


}
