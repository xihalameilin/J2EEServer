package com.example.demo.controller;


import com.example.demo.Enity.User;
import com.example.demo.service.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/QueryController")
public class QueryController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private DishService dishService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private AnalysisService analysisService;

    @GetMapping("/getAll")
    public String getAllShops(){
        return new JSONArray(shopService.getAllShops()).toString();
    }

    @GetMapping("/getAllShopsNew")
    public String getAllShopsNew(){
        return new JSONArray(shopService.getAllShopsNew()).toString();
    }

    @GetMapping("/getAllDishes/{shopID}")
    public String getAllDishesByShopID(@PathVariable String shopID){
        return new JSONArray(dishService.getAllDishesByShopID(shopID)).toString();
    }

    @GetMapping("/getAllAddresses/{userID}")
    public String getAllAddressesByUserID(@PathVariable String userID){
        return new JSONArray(addressService.getAllAddressesByUserID(userID)).toString();
    }


    @GetMapping("/getAllOrders/{userID}/{state}")
    public String getAllOrdersByState(@PathVariable String userID,@PathVariable Integer state){
        return new JSONArray(orderService.getAllOrdersByState(userID,state)).toString();
    }

    @GetMapping("/getAllOrdersByShopID/{shopID}")
    public String getAllOrdersByShopID(@PathVariable String shopID){
        return new JSONArray(orderService.getAllOrdersByShopID(shopID)).toString();
    }

    @GetMapping("/getAllOrderItems/{orderID}")
    public String getAllOrdersByState(@PathVariable Integer orderID){
        return new JSONArray(orderService.getAllOrderItemsByOrderID(orderID)).toString();
    }

    @GetMapping("/getUser/{userID}")
    public String getUserByUserID(@PathVariable String userID){
        return new JSONObject(userService.getUserByID(userID)).toString();
    }

    @GetMapping("/getAllShopChange")
    public String getAllShopChange(){
        return new JSONArray(shopService.getAllShopInfoChange()).toString();
    }

    @GetMapping("/getAllMoneyRecord")
    public String getAllMoneyRecord(){
        return new JSONArray(orderService.getAllMoneyRecord()).toString();
    }



    @GetMapping("/getdata")
    public String getdata(){
        int userCount = analysisService.getUserCount();
        int shopCount = analysisService.getShopCount();
        double total = analysisService.getTotal();
        return userCount+" "+shopCount+" "+total;
    }

}
