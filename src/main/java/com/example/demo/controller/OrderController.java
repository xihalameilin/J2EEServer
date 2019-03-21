package com.example.demo.controller;


import com.example.demo.Enity.Order;
import com.example.demo.Enity.OrderItem;
import com.example.demo.service.OrderService;
import com.example.demo.service.ShopService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/OrderController")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ShopService shopService;


    @PostMapping("/insertOrder/{userID}/{shopID}/{address}/{total}")
    public Integer insertOrder(@PathVariable String userID,@PathVariable  String shopID,
                            @PathVariable  String address,  @PathVariable  double total,
                            HttpServletRequest request){
        Order order = new Order();
        order.setDate(new Date());
        order.setShopID(shopID);
        order.setUserID(userID);
        order.setTotal(total);
        String items = request.getParameter("item");
        Set<OrderItem> orderItems = stringToOrderItems(items);
        for(OrderItem orderItem : orderItems){
            orderItem.setOrder(order);
        }
        order.setOrderItems(orderItems);
        order.setState(0);
        order.setAddress(address);

        return  orderService.insertOrder(order);
    }


    @PostMapping("/changeState/{orderID}/{state}")
    public void changeState(@PathVariable Integer orderID,@PathVariable int state){
        orderService.changeState(orderID,state);
    }












    private Set<OrderItem> stringToOrderItems(String s) {
        Set<OrderItem> orderItems = new HashSet<>();
        String[] strings = s.substring(1,s.length()-1).split(",");
        for(int i=0;i<strings.length;i++){
            String[] strings1 = strings[i].substring(1,strings[i].length()-1).split(" ");
            OrderItem orderItem = new OrderItem();
            orderItem.setName(strings1[1]);
            orderItem.setNum(Integer.valueOf(strings1[3]));
            orderItem.setPrice(Double.valueOf(strings1[2]));
            orderItem.setTotal(orderItem.getPrice()*orderItem.getNum());
            orderItems.add(orderItem);
        }
        return orderItems;
    }


}
