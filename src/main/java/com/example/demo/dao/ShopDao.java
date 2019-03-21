package com.example.demo.dao;

import com.example.demo.Enity.Shop;
import java.util.*;

public interface ShopDao {

    public List<Shop> getAllShops();

    public void insertShop(Shop shop);

    public Shop getShopByShopID(String shopID);

    public void changeState(String shopID,Integer state);

    public List<Shop> getAllShopsNew();

    public void updateShop(String shopID,String address,String type);

    public void changeTotal(String shopID,double total);

    public int getCount();

    public double getTotal();
}
