package com.example.demo.Enity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "shops")
public class Shop {

    @Id
    private String shopID;

    private String name;

    private String address;

    private Integer state;

    private String type;

    private double total;


    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @OneToMany(mappedBy = "shop",cascade = {CascadeType.ALL})
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Set<Dish> dishes;

    public String getShopID() {
        return shopID;
    }

    public void setShopID(String shopID) {
        this.shopID = shopID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
