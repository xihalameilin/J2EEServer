package com.example.demo.controller;


import com.example.demo.Enity.Dish;
import com.example.demo.Enity.Shop;
import com.example.demo.Enity.ShopInfoChange;
import com.example.demo.service.ShopService;
import java.util.*;

import com.example.demo.serviceImpl.ShopServiceImpl;
import net.sf.json.JSONArray ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/ShopController")
public class ShopController {

    @Autowired
    private ShopService shopService;




//    @GetMapping("/getAll")
//    public String getAllShops(){
//        System.out.println("得到所有的shop");
//        System.out.println(shopService.getAllShops().size());
//        return org.json.JSONArray.;
//    }

    @GetMapping("/getShopID")
    public String getShopID(){
        return shopService.getShopID();
    }

    @PostMapping("/registerShop")
    public void registerShop(HttpServletRequest httpServletRequest){
        String shopID = httpServletRequest.getParameter("shopID");
        String name = httpServletRequest.getParameter("name");
        String dish = httpServletRequest.getParameter("dish");
        String prices = httpServletRequest.getParameter("price");
        String address = httpServletRequest.getParameter("address");
        String type = httpServletRequest.getParameter("type");


        Shop shop = new Shop();
        shop.setShopID(shopID);
        shop.setName(name);
        shop.setAddress(address);
        shop.setState(0);
        shop.setType(type);


        JSONArray jsonArray= JSONArray.fromObject(dish);
        List<String> strings = JSONArray.toList(jsonArray,String.class);

        JSONArray jsonArray2= JSONArray.fromObject(prices);
        List<Double> price = JSONArray.toList(jsonArray2,Double.class);

        Set<Dish> dishes = new HashSet<>();
        for(int i=0;i<jsonArray.size();i++){
            Dish d = new Dish();
            d.setName(strings.get(i));
            d.setPrice(price.get(i));
            d.setShop(shop);
            dishes.add(d);
        }
        shop.setDishes(dishes);
        shopService.registerShop(shop);
    }


    @PostMapping("/changeState/{shopID}/{state}")
    public void changeState(@PathVariable String shopID,@PathVariable Integer state){

        shopService.changeShopState(shopID,state);
    }


    @PostMapping("/changeShopInfoState/{id}/{state}/{address}/{type}/{shopID}")
    public void changeShopInfoState(@PathVariable Integer id,@PathVariable int state,@PathVariable String address,@PathVariable String type,@PathVariable String shopID){
        shopService.changeShopInfoState(id,state,address,type,shopID);
    }


    @PostMapping("/insertShopInfo/{shopID}/{address}/{type}")
    public void insertShopInfo(@PathVariable String shopID,@PathVariable String address,@PathVariable String type){
        ShopInfoChange shopInfoChange = new ShopInfoChange();
        shopInfoChange.setAddress(address);
        shopInfoChange.setShopID(shopID);
        shopInfoChange.setType(type);
        shopInfoChange.setState(0);
        shopService.insertInfo(shopInfoChange);
    }

    @PostMapping("/changeMoneyRecordState/{id}/{state}/{total}/{shopID}")
    public void changeMoneyRecordState(@PathVariable Integer id,@PathVariable int state,@PathVariable double total,@PathVariable String shopID){
        shopService.changeMoneyRecordState(id,state,total,shopID);
    }





    public static void main(String[] args){
        Shop shop = new Shop();
        shop.setShopID("0000001");
        shop.setName("黄焖鸡");
        shop.setAddress("123");
        Set<Dish> dishes = new HashSet<>();
        Dish d1 = new Dish();
        d1.setShop(shop);
        d1.setName("鸡肉");

        Dish d2 = new Dish();
        d2.setShop(shop);
        d2.setName("鱼肉");

        dishes.add(d1);
        dishes.add(d2);

        shop.setDishes(dishes);
        ShopService shopService = new ShopServiceImpl();
        shopService.registerShop(shop);
    }
}
