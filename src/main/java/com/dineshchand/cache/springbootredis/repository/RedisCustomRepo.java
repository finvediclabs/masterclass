
package com.dineshchand.cache.springbootredis.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.dineshchand.cache.springbootredis.entity.Order;
import com.dineshchand.cache.springbootredis.model.OrderRequest;

@Repository
public class RedisCustomRepo {

	private HashOperations<String, String, Order> hashOperations;

	private RedisTemplate<String, ?> redisTemplate;

	public RedisCustomRepo(RedisTemplate<String, ?> redisTemplate) {
		this.redisTemplate = redisTemplate;
		this.hashOperations = this.redisTemplate.opsForHash();
	}

	public void save(OrderRequest orderRequest) {
		Order order = Order.builder().amount(orderRequest.getTotalAmount()).orderStatus("CREATED")
				.productId(orderRequest.getProductId()).orderDate(Instant.now()).quantity(orderRequest.getQuantity())
				.build();
		hashOperations.put("PRODUCT", String.valueOf(orderRequest.getProductId()), order);
	}

	public List<Order> findAll() {
		return hashOperations.values("PRODUCT");
	}

	public Order findById(String id) {
		return (Order) hashOperations.get("PRODUCT", id);
	}

	public void update(OrderRequest user) {
		save(user);
	}

	public void delete(String id) {
		hashOperations.delete("PRODUCT", id);
	}

	public List<Order> multiGetUsers(List<String> userIds) {
		return hashOperations.multiGet("PRODUCT", userIds);
	}
}
