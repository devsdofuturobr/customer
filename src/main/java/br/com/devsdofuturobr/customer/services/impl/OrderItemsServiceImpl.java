package br.com.devsdofuturobr.customer.services.impl;

import br.com.devsdofuturobr.customer.dto.request.OrderItemFilter;
import br.com.devsdofuturobr.customer.dto.request.OrderItemRequest;
import br.com.devsdofuturobr.customer.entities.Order;
import br.com.devsdofuturobr.customer.entities.OrderItem;
import br.com.devsdofuturobr.customer.entities.OrderItemId;
import br.com.devsdofuturobr.customer.entities.Product;
import br.com.devsdofuturobr.customer.exception.OrderItemDTOException;
import br.com.devsdofuturobr.customer.exception.OrderItemIdAlreadyExistsException;
import br.com.devsdofuturobr.customer.exception.OrderItemNotFoundException;
import br.com.devsdofuturobr.customer.mappers.OrderItemMapper;
import br.com.devsdofuturobr.customer.repositories.OrderItemRepository;
import br.com.devsdofuturobr.customer.services.OrderItemsService;
import br.com.devsdofuturobr.customer.services.OrderService;
import br.com.devsdofuturobr.customer.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class OrderItemsServiceImpl implements OrderItemsService {

    private final OrderItemRepository orderItemRepository;
    private final ProductService productService;
    private final OrderService orderService;

    @Override
    public OrderItem create(OrderItemRequest orderItemDTO){


        validationOrderItemDtoIsNull(orderItemDTO);
        validationOrderItemDtoInvalidQuantity(orderItemDTO);

        var order = orderService.findById(orderItemDTO.orderId());
        var product = productService.findById(orderItemDTO.productId());

        var orderItemId = makeId(order, product);

        if (orderItemRepository.existsById(orderItemId)) {
            throw new OrderItemIdAlreadyExistsException();
        }

        var orderItem = OrderItemMapper.toCreateByDTO(orderItemDTO, order, product, orderItemId);
        return orderItemRepository.save(orderItem);
    }

    @Override
    public OrderItem findById(OrderItemFilter filter) {
        OrderItemId orderItemId = OrderItemId.builder()
                .orderId(filter.orderId())
                .orderItem(filter.orderItem())
                .productId(filter.productId())
                .build();
        return orderItemRepository.findById(orderItemId).orElseThrow(OrderItemNotFoundException::new);
    }

    @Override
    public Page<OrderItem> findAll(Pageable pageable) {
        return orderItemRepository.findAll(pageable);
    }

    @Override
    public void delete(OrderItemFilter filter) {
        OrderItem byId = findById(filter);
        orderItemRepository.delete(byId);
    }

    private void validationOrderItemDtoIsNull(OrderItemRequest orderItemDTO) {
        if (Objects.isNull(orderItemDTO)) {
            throw new OrderItemDTOException("Item cannot be null");
        }
    }

    private void validationOrderItemDtoInvalidQuantity(OrderItemRequest orderItemDTO) {
        if (orderItemDTO.quantity() <= 0) {
            throw new OrderItemDTOException("Quantity cannot be zero or negative number.");
        }
    }

    private OrderItemId makeId(Order order, Product product) {
        Integer lastOrderItem = orderItemRepository.findMaxOrderItem(order.getId());
        Integer newOrderItem = (lastOrderItem == null) ? 1 : lastOrderItem + 1;

        return OrderItemId.builder()
                .orderId(order.getId())
                .orderItem(newOrderItem)
                .productId(product.getId())
                .build();

    }
}
