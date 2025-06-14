package com.example.Ecomm.service;

import com.example.Ecomm.dto.OrderDTO;
import com.example.Ecomm.dto.OrderItemDTO;
import com.example.Ecomm.model.OrderItem;
import com.example.Ecomm.model.Orders;
import com.example.Ecomm.model.Product;
import com.example.Ecomm.model.User;
import com.example.Ecomm.repo.OrderRepository;
import com.example.Ecomm.repo.ProductRepository;
import com.example.Ecomm.repo.UserRepository;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;
    public OrderDTO placeOrder(Long userId, Map<Long, Integer> productQuantities, double totalAmount) {
     User user= userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User not found"));

        Orders order = new Orders();
        order.setUser(user);
        order.setOrderDate(new Date());
        order.setStatus("Pending");
        order.setTotalAmount(totalAmount);

        List<OrderItem> orderItems=new ArrayList<>();
        List<OrderItemDTO> orderItemDTOS=new ArrayList<>();

        for(Map.Entry<Long,Integer> entry:productQuantities.entrySet()) {
            Product product = productRepository.findById(entry.getKey())
                    .orElseThrow(() -> new RuntimeException("Product not found"));


            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(entry.getValue());
            orderItems.add(orderItem);

            orderItemDTOS.add(new OrderItemDTO(product.getName(), product.getPrice(), entry.getValue()));
        }
        order.setOrderItems(orderItems);
        Orders saveOrder= orderRepository.save(order);
        return new OrderDTO(saveOrder.getId(),saveOrder.getTotalAmount(),
                    saveOrder.getStatus(), saveOrder.getOrderDate(),orderItemDTOS);
    }

    public List<OrderDTO> getAllOrders() {
        List<Orders> orders= orderRepository.findAllOrdersWithUsers();
        return  orders.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private OrderDTO convertToDTO(Orders orders){
        List<OrderItemDTO> OrdersItems = orders.getOrderItems().stream()
        .map(item -> new OrderItemDTO(
                item.getProduct().getName(),
                item.getProduct().getPrice(),
                item.getQuantity())).collect(Collectors.toList());
        return new OrderDTO(
                orders.getId(),
                orders.getTotalAmount(),
                orders.getStatus(),
                orders.getOrderDate(),
                orders.getUser()!=null ? orders.getUser().getName() : "Unknown",
                orders.getUser()!=null ? orders.getUser().getEmail() : "Unknown",
                OrdersItems
        );
    }

    public List<OrderDTO> getOrderByUser(Long userId) {
       Optional<User> userOp= userRepository.findById(userId);
       if(userOp.isEmpty()){
           throw new RuntimeException("User not found");
       }
       User user =userOp.get();
       List<Orders> ordersList= orderRepository.findByUser(user);
       return ordersList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
}
