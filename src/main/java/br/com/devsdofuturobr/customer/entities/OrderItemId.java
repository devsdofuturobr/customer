package br.com.devsdofuturobr.customer.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemId implements java.io.Serializable {

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "order_item")
    private Integer orderItem;
}