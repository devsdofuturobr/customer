package br.com.devsdofuturobr.customer.services.impl;

import br.com.devsdofuturobr.customer.dto.request.OrderItemRequest;
import br.com.devsdofuturobr.customer.entities.Order;
import br.com.devsdofuturobr.customer.entities.OrderItem;
import br.com.devsdofuturobr.customer.entities.OrderItemId;
import br.com.devsdofuturobr.customer.entities.Product;
import br.com.devsdofuturobr.customer.exception.OrderItemDTOException;
import br.com.devsdofuturobr.customer.repositories.OrderItemRepository;
import br.com.devsdofuturobr.customer.services.OrderItemsService;
import br.com.devsdofuturobr.customer.services.OrderService;
import br.com.devsdofuturobr.customer.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class OrderItemsServiceImpl implements OrderItemsService {

    private final OrderItemRepository orderItemRepository;
    private final ProductService productService;
    private final OrderService orderService;

    @Override
    public OrderItem create(OrderItemRequest orderItemDTO) {

        validationOrderItemDtoIsNull(orderItemDTO);
        validationOrderItemDtoInvalidQuantity(orderItemDTO);

        var order = orderService.findById(orderItemDTO.orderId());
        var product = productService.findById(orderItemDTO.productId());

        var orderItemId = makeId(order, product);

        var orderItem = OrderItem.builder()
                .id(orderItemId)
                .order(order)
                .product(product)
                .quantity(orderItemDTO.quantity())
                .itemPrice(product.getPrice())
                .itemPriceTotal(product.getPrice().multiply(BigDecimal.valueOf(orderItemDTO.quantity())))
                .build();

        return orderItemRepository.save(orderItem);
    }

    private void validationOrderItemDtoIsNull(OrderItemRequest orderItemDTO) {
        if(Objects.isNull(orderItemDTO)) {
            throw new OrderItemDTOException("Item cannot be null");
        }
    }
    private void validationOrderItemDtoInvalidQuantity(OrderItemRequest orderItemDTO) {
        if(orderItemDTO.quantity() <= 0) {
            throw new OrderItemDTOException("Quantity cannot be zero or negative number.");
        }
    }

    private OrderItemId makeId(Order order, Product product){
        // Buscar o maior order_item já cadastrado para esse pedido
        Integer lastOrderItem = orderItemRepository.findMaxOrderItem(order.getId());
        Integer newOrderItem = (lastOrderItem == null) ? 1 : lastOrderItem + 1;

        OrderItemId orderItemId = new OrderItemId();
        orderItemId.setOrderId(order.getId());
        orderItemId.setOrderItem(newOrderItem); // Agora garantimos que o número do item seja incremental
        orderItemId.setProductId(product.getId());

        return orderItemId;
    }
}
