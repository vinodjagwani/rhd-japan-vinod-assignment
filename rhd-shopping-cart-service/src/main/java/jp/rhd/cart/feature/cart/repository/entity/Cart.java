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

@Setter
@Getter
@Table("cart")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cart implements Persistable<String> {

    static final long serialVersionUID = -501111492884005133L;

    @Id
    @Column
    String cartId;

    @Column
    String userId;

    @Column
    String sessionId;


    @Column
    String firstName;


    @Column
    String lastName;

    @Override
    public String getId() {
        return cartId;
    }

    @Override
    public boolean isNew() {
        return true;
    }

    // @Column
    // String mobile;

    //@Column
    //String email;

    //@Column
    //String address;
}
