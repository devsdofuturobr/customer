package br.com.devsdofuturobr.customer.services.impl;

import br.com.devsdofuturobr.customer.dto.OrderItemDTO;
import br.com.devsdofuturobr.customer.entities.Order;
import br.com.devsdofuturobr.customer.entities.OrderItem;
import br.com.devsdofuturobr.customer.entities.OrderItemId;
import br.com.devsdofuturobr.customer.entities.Product;
import br.com.devsdofuturobr.customer.repositories.OrderItemRepository;
import br.com.devsdofuturobr.customer.services.OrderItemsService;
import br.com.devsdofuturobr.customer.services.OrderService;
import br.com.devsdofuturobr.customer.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderItemsServiceImpl implements OrderItemsService {

    private final OrderItemRepository orderItemRepository;
    private final ProductService productService;
    private final OrderService orderService;

    @Override
    public OrderItem create(OrderItemDTO orderItemDTO) {
        var order = orderService.findById(orderItemDTO.orderId());
        var product = productService.findById(orderItemDTO.productId());

        var orderItemId = makeId(order, product);

        var orderItem = OrderItem.builder()
                .id(orderItemId)
                .order(order)
                .product(product)
                .quantity(orderItemDTO.quantity())
                .itemPrice(product.getPrice())
                .build();

        return orderItemRepository.save(orderItem);
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
