/**
 * Author: Vinod Jagwani
 */
package jp.rhd.cart.feature.cart.repository.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
@Table("cart_item")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItem implements Persistable<String>, Serializable {

    static final long serialVersionUID = -537111492884005133L;

    @Id
    @Column
    String cartItemId;

    @Column
    String cartId;

    @Column
    String itemId;

    @Column
    BigDecimal price;

    @Column
    Integer quantity;

    @Override
    public String getId() {
        return cartItemId;
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
