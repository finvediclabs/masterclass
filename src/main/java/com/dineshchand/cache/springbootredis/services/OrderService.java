package com.dineshchand.cache.springbootredis.services;

import java.util.List;

import com.dineshchand.cache.springbootredis.entity.Order;
import com.dineshchand.cache.springbootredis.model.OrderRequest;
import com.dineshchand.cache.springbootredis.model.OrderResponse;

public interface OrderService {
    long placeOrder(OrderRequest orderRequest);

    OrderResponse getOrderDetailsById(long orderId) throws Exception;

	List<Order> findAll();
}
