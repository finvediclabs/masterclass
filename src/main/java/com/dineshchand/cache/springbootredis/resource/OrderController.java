package com.dineshchand.cache.springbootredis.resource;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dineshchand.cache.springbootredis.entity.Order;
import com.dineshchand.cache.springbootredis.model.OrderRequest;
import com.dineshchand.cache.springbootredis.model.OrderResponse;
import com.dineshchand.cache.springbootredis.services.OrderService;

@RestController
@RequestMapping("/db")
public class OrderController {
	Logger log = Logger.getLogger("OrderController.class");
    @Autowired
    private OrderService orderService;

    
    @PostMapping("/placeOrder")
    public ResponseEntity<OrderRequest> placeOrder(@RequestBody OrderRequest orderRequest) {
        long orderId = orderService.placeOrder(orderRequest);
        log.info("Order id has been submitted:"+ orderId);
        orderRequest.setId(orderId);
        return new ResponseEntity<>(orderRequest, HttpStatus.OK);
    }
    
    @GetMapping("/findall")
    public List<Order> findAll() throws Exception {
        return  orderService.findAll();
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderDetailsById(@PathVariable long orderId) throws Exception {
        OrderResponse orderResponse =
                orderService.getOrderDetailsById(orderId);

        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

}
