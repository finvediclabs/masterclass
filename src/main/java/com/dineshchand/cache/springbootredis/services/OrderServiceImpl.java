package com.dineshchand.cache.springbootredis.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dineshchand.cache.springbootredis.entity.Order;
import com.dineshchand.cache.springbootredis.model.OrderRequest;
import com.dineshchand.cache.springbootredis.model.OrderResponse;
import com.dineshchand.cache.springbootredis.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	Logger log = Logger.getLogger("OrderServiceImpl.class");
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public long placeOrder(OrderRequest orderRequest) {

		log.info("Placing order request: {}" + orderRequest);

		log.info("Creating order with status CREATED!");
		Order order = Order.builder().amount(orderRequest.getTotalAmount()).orderStatus("CREATED")
				.productId(orderRequest.getProductId()).orderDate(Instant.now()).quantity(orderRequest.getQuantity())
				.build();
		order = orderRepository.save(order);

		log.info("Calling payment service to complete the payment!");

		String orderStatus = null;

		order.setOrderStatus("PLACED");
		orderRepository.save(order);

		log.info("Order places successfully with order id: {}" + order.getId());

		// BeanUtils.copyProperties(orderRequest, );
		return order.getId();
	}
	
	@Override
    public List<Order> findAll() {
		return orderRepository.findAll();
		
	}

	@Override
    public OrderResponse getOrderDetailsById(long orderId) throws Exception{

        log.info("Get order details for order id {}"+ orderId);

        Optional<Order> order = orderRepository.findById(orderId);
        if(order.isEmpty()) {
             
                   throw  new Exception("Order with id " + orderId +
                                " not found!");
        }
       

        log.info("Getting payment information from payment service");
       

        log.info("Getting order details for order id {}"+orderId);
        Order actOrder = order.get();
        return OrderResponse.builder()
                .orderStatus(actOrder.getOrderStatus())
                .orderDate(actOrder.getOrderDate())
                .orderId(actOrder.getId())
                .amount(actOrder.getAmount())
                .build();

    }
}
