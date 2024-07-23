package com.dineshchand.cache.springbootredis.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dineshchand.cache.springbootredis.entity.Order;
import com.dineshchand.cache.springbootredis.model.OrderRequest;
import com.dineshchand.cache.springbootredis.repository.RedisCustomRepo;

@RestController
@RequestMapping("/redis")
public class RedisResource {
	
	@Autowired
    private RedisCustomRepo userRepository;

    @PostMapping("/placeOrder")
    public OrderRequest save(@RequestBody OrderRequest orderRequest){
        userRepository.save(orderRequest);
        return orderRequest;
    }

    @GetMapping("/findall")
    public List<Order> findAll(){
        return userRepository.findAll();
    }

    @GetMapping("/getorder/{id}")
    public Order getUser(@PathVariable String id){
        return userRepository.findById(id);
    }

    @PutMapping("/update")
    public OrderRequest update(@RequestBody OrderRequest user){
        userRepository.update(user);
        return user;
    }

    @DeleteMapping("/{orderId}")
    public String deleteUser(@PathVariable String id){
        userRepository.delete(id);
        return id;
    }

 

  
  

}
