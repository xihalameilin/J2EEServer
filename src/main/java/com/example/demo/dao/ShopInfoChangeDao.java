package com.example.demo.dao;

import com.example.demo.Enity.ShopInfoChange;
import java.util.*;

public interface ShopInfoChangeDao {

    public void insertInfo(ShopInfoChange shopInfoChange);

    public List<ShopInfoChange> getAll();

    public void changeState(Integer id,Integer state);
}
