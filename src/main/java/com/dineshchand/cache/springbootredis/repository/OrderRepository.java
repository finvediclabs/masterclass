package com.dineshchand.cache.springbootredis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dineshchand.cache.springbootredis.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
