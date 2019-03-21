package com.example.demo.controller;

import com.example.demo.Enity.Address;
import com.example.demo.Enity.Requestor;
import com.example.demo.Enity.User;
import com.example.demo.service.EmailService;
import com.example.demo.service.ShopService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/LoginController")
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private ShopService shopService;


    @GetMapping("/login/{userID}/{password}")
    public String login(@PathVariable("userID")String userID, @PathVariable("password")String password){
        return userService.login(userID,password);
    }

    @PostMapping("/userRegister/{id}/{email}/{code}/{password}/{telephone}")
    public boolean registerUser(@PathVariable String id,@PathVariable String email,@PathVariable String code,
                             @PathVariable String password,@PathVariable String telephone,HttpServletRequest request){

        String address = request.getParameter("address");
        Set<Address> addresses = stringToAddress(address);
        User user = new User();
        user.setId(id);
        user.setTelephone(telephone);
        user.setEmail(email);
        user.setPassword(password);
        user.setPoint(0);
        user.setTotal(0);
        for(Address address1 : addresses){
            address1.setUser(user);
        }
        user.setAddress(addresses);

        String checkCode = emailService.getIndentityCode();
        if(checkCode.equals(code)){
            userService.registerUser(user);
            return true;
        }
        return false;
    }

    @PostMapping("/requestorRegister/{id}/{email}/{code}/{password}/{telephone}")
    public boolean registerRequestor(@PathVariable String id,@PathVariable String email,@PathVariable String code,
                                  @PathVariable String password,@PathVariable String telephone){
        System.out.println(email+telephone+id+password);
        System.out.println("trigger-----------------------------------");
       Requestor requestor = new Requestor();
       requestor.setId(id);
       requestor.setEmail(email);
       requestor.setPassword(password);
       requestor.setTelephone(telephone);
       String checkCode = emailService.getIndentityCode();
       if(checkCode.equals(code)){
           userService.registerRequestor(requestor);
           System.out.println("success-----------------------------------");
           return true;
       }
       return false;
    }

    @GetMapping("/CheckUserID/{userID}")
    public boolean isUserIDValid(@PathVariable  String userID){
        return userService.isValid(userID);
    }

    @GetMapping("/CheckEmail/{email}")
    public boolean isEmailIDValid(@PathVariable  String email){
        return userService.isEmailValid(email);
    }

    @GetMapping("/SendMail/{email}")
    public void sendIdentityCode(@PathVariable String email){
        try {
            emailService.sendMessage(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @GetMapping("/getShopID")
    public String getShopID(){
        System.out.println(shopService.getShopID());
        return shopService.getShopID();
    }


    @PostMapping("/update/{userID}/{email}/{telephone}")
    public void update(@PathVariable String userID,
                       @PathVariable String email,
                       @PathVariable String telephone){
        User user = userService.getUserByID(userID);
        user.setEmail(email);
        user.setTelephone(telephone);
        userService.updateUser(user);
    }


    private Set<Address> stringToAddress(String s){
        Set<Address> addresses = new HashSet<>();
        s = s.substring(1,s.length()-1);
        String[] strings = s.split(",");
        for(String s1 : strings){
            Address address = new Address();
            System.out.println(s1.substring(1,s1.length()-1));
            address.setAddress(s1.substring(1,s1.length()-1));
            addresses.add(address);
        }
        return addresses;
    }


}
