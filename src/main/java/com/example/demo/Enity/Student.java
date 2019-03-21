//package com.example.demo.Enity;
//
//
//
//import org.hibernate.annotations.Cascade;
//
//import javax.persistence.*;
//import java.util.HashSet;
//import java.util.Set;
//
//@Entity
//@Table(name = "students")
//public class Student {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    private String name;
//
//    @OneToMany(mappedBy = "student",cascade = {CascadeType.ALL})
//    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
//    private Set<Address> address = new HashSet<>();
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public Set<Address> getAddress() {
//        return address;
//    }
//
//    public void setAddress(Set<Address> address) {
//        this.address = address;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//}
